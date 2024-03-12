package com.yusuforhan.booksapp.android.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuforhan.booksapp.android.presentation.theme.Primary

@Composable
fun BAButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = Primary,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Preview
@Composable
fun BAButtonPreview() {
    BAButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        text = "Goto Pay"
    )
}