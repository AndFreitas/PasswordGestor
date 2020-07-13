package ipca.gestorpasswords.passwordgestor

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import ipca.gestorpasswords.passwordgestor.room.AppDataBase
import ipca.gestorpasswords.passwordgestor.room.PasswordEntity
import kotlinx.android.synthetic.main.fragment_add_password.*
import kotlinx.coroutines.launch


class AddPasswordFragment : BaseFragment() {

    private var password : PasswordEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            password = AddPasswordFragmentArgs.fromBundle(it).password
            editTextPassword.setText(password?.password)
            editTextSite.setText(password?.site)
            editTextDescricao.setText(password?.descricao)
        }

        btn_Save.setOnClickListener {view ->

            val Password = editTextPassword.text.toString().trim()
            val Site = editTextSite.text.toString().trim()
            val Descricao = editTextDescricao.text.toString().trim()

            if (Password.isEmpty()) {
                editTextPassword.error = "Password necessária"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            if (Site.isEmpty()) {
                editTextSite.error = "Site Necessario"
                editTextSite.requestFocus()
            }

            if (Descricao.isEmpty()) {
                editTextDescricao.error = "Descricao"
                editTextDescricao.requestFocus()
            }

            launch {
                context?.let {
                    val mpassword = PasswordEntity(Password,Site,Descricao)

                    if(password == null){
                        AppDataBase(it).getPasswordDao().addPassword(mpassword)
                        it.toast("Password Guardada")
                    }else {
                        mpassword.id = password!!.id
                        AppDataBase(it).getPasswordDao().updatePassword(mpassword)
                        it.toast("Password Guardada")
                    }
                    val action = AddPasswordFragmentDirections.ActionSavePassword()
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    private fun deletePassword(){
        AlertDialog.Builder(context).apply {
            setTitle("Tens a certeza?")
            setMessage("Não poderá voltar atrás com esta operação")
            setPositiveButton("Sim"){_, _ ->
                launch { AppDataBase(context).getPasswordDao().deletePassword(password!!) }
                val action = AddPasswordFragmentDirections.ActionSavePassword()
                Navigation.findNavController(view!!).navigate(action)
            }
            setNegativeButton("Nao"){_, _ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.delete-> if (password != null) deletePassword() else context?.toast("Não pode ser apagado")
      }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

}
