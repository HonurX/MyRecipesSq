package com.onurgunes.myrecipes.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import com.google.android.material.snackbar.Snackbar
import com.onurgunes.myrecipes.databinding.FragmentMealListBinding
import com.onurgunes.myrecipes.databinding.FragmentRecipeAddBinding
import kotlinx.coroutines.sync.Semaphore
import java.io.IOException


class RecipeAddFragment : Fragment() {
    private var _binding: FragmentRecipeAddBinding? = null
 private lateinit var permissionLauncher : ActivityResultLauncher<String>
 private lateinit var  activityResultLauncher: ActivityResultLauncher<Intent>
 private var secilenGorsel : Uri? = null
    private var secilenBitmap : Bitmap? = null


    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecipeAddBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLauncher()

        binding!!.imageView.setOnClickListener { selectImage(it) }
        binding!!.buttonDelete.setOnClickListener { delete(it) }
        binding!!.buttonSave.setOnClickListener { save(it) }



    }


    fun save(view: View) {

    }

    fun delete(view: View) {

    }


    fun selectImage(view: View)  {
 if (ContextCompat.checkSelfPermission(requireActivity().applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
     if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)) {
         Snackbar.make(view,"show me password", Snackbar.LENGTH_INDEFINITE).setAction("passsaword",View.OnClickListener {
             permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
         }).show()
     } else {
         permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
     }
 }

        else {
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }

    }


     private fun  registerLauncher () {


         activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
             if (result.resultCode == AppCompatActivity.RESULT_OK) {
                 val resultFromGalery = result.data
                 if (resultFromGalery != null) {
                     secilenGorsel = resultFromGalery.data


                      try {
                          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                              val source =  ImageDecoder.createSource(requireActivity().contentResolver,
                                  secilenGorsel!!
                              )

                              secilenBitmap = ImageDecoder.decodeBitmap(source)
                              binding?.imageView?.setImageBitmap(secilenBitmap)
                          } else {

                              secilenBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,secilenGorsel)
                              binding?.imageView?.setImageBitmap(secilenBitmap)
                          }

                      } catch (e:Exception) {
                          e.localizedMessage
                      }


                 }
             }

         }



          permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
              if (result) {
                  /// izin verildi
                  val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                  activityResultLauncher.launch(intentToGallery)
              } else {

                  Toast.makeText(requireActivity(),"GIVE PERMISSON " , Toast.LENGTH_LONG).show()

              }

          }

     }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}









