package id.ac.ubaya.informatika.adv160420099week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.adv160420099week4.R
import id.ac.ubaya.informatika.adv160420099week4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()

        var studentID = view.findViewById<TextInputEditText>(R.id.txtID)
        var studentName = view.findViewById<TextInputEditText>(R.id.txtName)
        var birthOfDate = view.findViewById<TextInputEditText>(R.id.txtBod)
        var phone = view.findViewById<TextInputEditText>(R.id.txtPhone)

        detailViewModel.studentLD.observe(viewLifecycleOwner){studentDetail ->
            studentID.setText(studentDetail.id.toString())
            studentName.setText(studentDetail.name.toString())
            birthOfDate.setText(studentDetail.dob.toString())
            phone.setText(studentDetail.phone.toString())
        }
    }
}