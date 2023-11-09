package com.example.kurilichevproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Правила для заголовков, заменяющие шрифт и некоторые размеры
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily.Serif
    ),
    titleMedium = TextStyle(fontFamily = FontFamily.Serif, fontSize = 24.sp),
    titleSmall = TextStyle(fontFamily = FontFamily.Serif)
)