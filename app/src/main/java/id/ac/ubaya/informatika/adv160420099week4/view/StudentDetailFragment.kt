package id.ac.ubaya.informatika.adv160420099week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.adv160420099week4.R
import id.ac.ubaya.informatika.adv160420099week4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.adv160420099week4.util.loadImage
import id.ac.ubaya.informatika.adv160420099week4.util.showNotification
import id.ac.ubaya.informatika.adv160420099week4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotificationClickListener, ButtonUpdateClickListener{
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.notiflistener = this
        dataBinding.updatelistener = this


//        var studentPhoto = view.findViewById<ImageView>(R.id.imageView2)
//        var progressBarDetail = view.findViewById<ProgressBar>(R.id.progressBarDetail)
//        var studentID = view.findViewById<TextInputEditText>(R.id.txtID)
//        var studentName = view.findViewById<TextInputEditText>(R.id.txtName)
//        var birthOfDate = view.findViewById<TextInputEditText>(R.id.txtBod)
//        var phone = view.findViewById<TextInputEditText>(R.id.txtPhone)
//        val btnNotif = view.findViewById<Button>(R.id.btnNotif)


//
//
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID)
        observeViewModel()
    //
//        detailViewModel.studentLD.observe(viewLifecycleOwner){studentDetail ->
//            studentPhoto.loadImage(studentDetail.photoUrl, progressBarDetail)
//            studentID.setText(studentDetail.id.toString())
//            studentName.setText(studentDetail.name.toString())
//            birthOfDate.setText(studentDetail.bod.toString())
//            phone.setText(studentDetail.phone.toString())
//
//            var student = studentDetail
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_mail_24)
//                    }
//            }
//        }
    }
    fun observeViewModel(){
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
        })
    }

    override fun onButtonUpdateClick(v: View) {

    }

    override fun onButtonNotificationClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        showNotification(dataBinding.student?.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_mail_24)
                    }
    }
}