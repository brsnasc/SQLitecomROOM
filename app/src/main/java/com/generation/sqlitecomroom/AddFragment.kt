package com.generation.sqlitecomroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.generation.sqlitecomroom.DATA.Usuario
import com.generation.sqlitecomroom.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonADD.setOnClickListener {
            inserirNoBanco()

        }

        return binding.root

    }

    fun verificarCampos(nome: String, sobrenome: String, idade: String): Boolean {
        return !(nome == "" || sobrenome == "" || idade == "")

    }

    fun inserirNoBanco(){
        val nome = binding.editNome.text.toString()
        val sobrenome = binding.editSobrenome.text.toString()
        val idade = binding.editIdade.text.toString()

        if(verificarCampos(nome, sobrenome, idade)) {
            val user = Usuario(0, nome, sobrenome, idade.toInt())
            mainViewModel.addUser(user)

            Toast.makeText(context, "Usuário Adicionado com Sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {

            Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()

        }
    }
}