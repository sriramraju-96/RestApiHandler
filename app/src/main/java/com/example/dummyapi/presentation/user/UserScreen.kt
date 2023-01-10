package com.example.dummyapi.presentation.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import com.example.dummyapi.domain.model.User
import com.example.dummyapi.domain.repository.UserRepository

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val list = state.users


    var entname by remember { mutableStateOf("") }
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        viewModel.getUsers()
        refreshing = false
    }


    val rstate = rememberPullRefreshState(refreshing, ::refresh)

//    LazyColumn {
//        items(list) { item ->
//            println(item.user_name)
//            Text(item.user_name)
//        }

    Column((Modifier.pullRefresh(rstate))) {
        Box {
            LazyColumn {
                if (!refreshing) {
                    items(list) { item ->
//                        println(item.user_name)
                        Text(item.username)

                    }
                }
            }

        }
        PullRefreshIndicator(refreshing, rstate, Modifier.align(Alignment.CenterHorizontally))
        OutlinedTextField(value = entname,
            onValueChange = { entname = it },
            label = { Text("Enter Name") })
        Button(onClick = { onSubmit(viewModel, entname) }) {
            Text("Click here!!")
        }
    }

}

fun onSubmit(viewModel: UserViewModel, name: String) {
    viewModel.addUser(User(name))
    viewModel.getUsers()
}



