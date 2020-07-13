package ipca.gestorpasswords.passwordgestor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ipca.gestorpasswords.passwordgestor.room.AppDataBase
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch


class MainFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RecyclerPasswordView.setHasFixedSize(true)
        RecyclerPasswordView.layoutManager =StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val passwords = AppDataBase(it).getPasswordDao().getAllPasswords()
                RecyclerPasswordView.adapter = PasswordAdapter(passwords)
            }
        }

        btn_Add.setOnClickListener{

            val action = MainFragmentDirections.actionAddPassword()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
