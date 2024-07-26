package com.example.techsrcstudioc.homePage.topbarComp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techsrcstudioc.R

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel

@Composable
fun SearchBar(
    navController: NavController,
    generalModel: GeneralViewModel,
    searchModel: SearchViewModel = viewModel()
) {
    val interactionSource2 = remember { MutableInteractionSource() }
    BasicTextField(
        value = searchModel.enteredSearch,
        onValueChange = { new ->
            searchModel.enteredSearch = new
        },
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(fontSize = 17.sp, lineHeight = 30.sp, fontWeight = FontWeight(500), color = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .border(width = 0.5.dp, color = Color.Red, shape = RoundedCornerShape(5.dp)),
        interactionSource = interactionSource2,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Default
        ),
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            value = searchModel.enteredSearch,
            singleLine = true,
            innerTextField = innerTextField,
            enabled = true,
            placeholder = {
                Text(
                    text = "Search here",
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    color =  Color.White
                )
            },
            trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        modifier = Modifier.size(19.dp),
                        contentDescription = "search icon",
                        tint = Color.Blue
                    )
            },
            interactionSource = interactionSource2,
            visualTransformation = VisualTransformation.None,
            contentPadding = PaddingValues(top = 14.dp, bottom = 14.dp, end = 15.dp, start = 15.dp),
        )
    }
}