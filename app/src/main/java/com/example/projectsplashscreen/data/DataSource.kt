package com.example.projectsplashscreen.data

import com.example.projectsplashscreen.R
import com.example.projectsplashscreen.presentation.common.DataClass1

// Returns a list of Data

class DataSource (){
    fun loadData(): List<DataClass1> {
        return listOf<DataClass1>(
            DataClass1(id = 1, title = "One", description = " one", image = R.drawable.archery_bow_svgrepo_com),
            DataClass1(id = 2, title = "Two", description = " Two", image = R.drawable.eye_drops_svgrepo_com),
            DataClass1(id = 3, title = "Three", description = " Three", image = R.drawable.magnetic_resonance_imaging_svgrepo_com),
            DataClass1(id = 4, title = "Four", description = " Four", image = R.drawable.samples_svgrepo_com),
            DataClass1(id = 5, title = "Five", description = " Five", image = R.drawable.strawberry_svgrepo_com)
        )
    }
}

