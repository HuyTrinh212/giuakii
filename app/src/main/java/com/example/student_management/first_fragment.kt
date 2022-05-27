package com.example.student_management


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_management.database.Student
import com.example.student_management.database.Student_Database
import com.example.student_management.model.Student_Entity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_first_fragment.*
import kotlinx.android.synthetic.main.activity_first_fragment.edt_grade
import kotlinx.android.synthetic.main.activity_first_fragment.edt_name

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class first_fragment : AppCompatActivity(), CoroutineScope {
    private var noteDB: Student_Database ?= null

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_fragment)
        supportActionBar?.setTitle("Thêm Sinh viên")
        mJob = Job()
        noteDB = Student_Database.getDatabase(this)


        btn_add.setOnClickListener {
            launch {
                val strName: String = edt_name.text.toString()
                val strGrade: String = edt_grade.text.toString()
                val strEmail: String = edt_name.text.toString()
                val strPhone: String = edt_grade.text.toString()
                val strAddress: String = edt_address.text.toString()

                if (strName =="" && strGrade == "" ) {
                    Toast.makeText( this@first_fragment,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show()

                } else if (strName =="" || strGrade == "") {
                   Toast.makeText(this@first_fragment,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show()

                } else {
                    noteDB?.studentDao()?.insert(Student_Entity(name = strName, grade = strGrade,email=strEmail,phone = strPhone,address = strAddress))
                    Toast.makeText(this@first_fragment,"Thêm Thành Công",Toast.LENGTH_SHORT).show()

                    finish()
                }

        }
}

}
}