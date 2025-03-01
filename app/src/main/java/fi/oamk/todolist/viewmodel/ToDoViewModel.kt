package fi.oamk.todolist.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.oamk.todolist.model.Todo
import fi.oamk.todolist.model.TodosApi
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel()
{
    var todos = mutableStateListOf<Todo>()
    private set

    init {
        getTodosList()
    }
    private fun getTodosList() {
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try {
                todosApi = TodosApi.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e: Exception) {
                Log.d("ERROR",e.message.toString())
            }
        }
    }
}