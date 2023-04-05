package id.ac.ubaya.informatika.adv160420099week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.adv160420099week4.R
import id.ac.ubaya.informatika.adv160420099week4.model.Student
import id.ac.ubaya.informatika.adv160420099week4.util.loadImage

class StudentListAdapter(val studenList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int):StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val txtID = holder.view.findViewById<TextView>(R.id.txtID)
        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)

        txtID.text = studenList[position].id
        txtName.text = studenList[position].name
        val studentID = studenList[position].id

        btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(studentID.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studenList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int {
        return studenList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}

