package com.project.biposadmin.presentation.add_product

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.project.biposadmin.R
import com.project.biposadmin.model.Product
import kotlinx.android.synthetic.main.fragment_product_popular.*
import kotlinx.android.synthetic.main.fragment_top_banner.btn_chooseFile
import kotlinx.android.synthetic.main.fragment_top_banner.btn_upload
import kotlinx.android.synthetic.main.fragment_top_banner.edt_fileName
import kotlinx.android.synthetic.main.fragment_top_banner.img_upload
import kotlinx.android.synthetic.main.fragment_top_banner.progressBar

class BestPriceFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1

    private lateinit var imageUri: Uri

    private lateinit var storageRef: StorageReference
    private lateinit var databaseRef: DatabaseReference
    private var uploadTask: StorageTask<*>? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        storageRef = FirebaseStorage.getInstance().getReference("products")
        databaseRef = FirebaseDatabase.getInstance().getReference("products")


        btn_chooseFile.setOnClickListener { openFileChooser() }
        btn_upload.setOnClickListener {
            if (uploadTask != null && uploadTask!!.isInProgress) {
                Toast.makeText(requireContext(), "Upload in progress", Toast.LENGTH_SHORT).show()
            } else {
                uploadFile()
            }
        }
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null
        ) {

            imageUri = data.data!!

            Glide.with(this)
                .load(imageUri)
                .into(img_upload)
        }
    }

    private fun getFileExtension(uri: Uri): String {
        val cR: ContentResolver = requireActivity().contentResolver
        val mime: MimeTypeMap = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))!!
    }

    private fun uploadFile() {
        val fileReference =
            storageRef.child("${System.currentTimeMillis()}.${getFileExtension(imageUri)}")

        uploadTask = fileReference.putFile(imageUri)
            .addOnSuccessListener {
                val handler = Handler()
                handler.postDelayed({
                    progressBar.progress = 0
                }, 3000)

                Toast.makeText(requireContext(), "Upload successful", Toast.LENGTH_SHORT).show()
                fileReference.downloadUrl.addOnSuccessListener {
                    val upload = Product(
                        id = (0..1000).random().toString(),
                        name = edt_fileName.text.toString().trim(),
                        image = it.toString(),
                        brand = edt_productBrand.text.toString(),
                        price = edt_productPrice.text.toString().toInt(),
                        phoneNumber = edt_phoneNumber.text.toString(),
                        description = edt_productDesc.text.toString(),
                        type = "best_price"
                    )
                    val uploadId = databaseRef.push().key
                    databaseRef.child(uploadId!!).setValue(upload)
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
            .addOnProgressListener { taskSnapshot ->
                val progress = (100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
                progressBar.progress = progress.toInt()
            }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_price, container, false)
    }

}