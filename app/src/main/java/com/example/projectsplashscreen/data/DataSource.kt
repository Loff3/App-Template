package com.example.projectsplashscreen.data

import com.example.projectsplashscreen.R

// Returns a list of Data

class DataSource (){
    fun loadData(): List<ItemDataClass> {
        return listOf<ItemDataClass>(
            ItemDataClass(id = 1, title = "One", description = " one", "1",image = R.drawable.archery_bow_svgrepo_com),
            ItemDataClass(id = 2, title = "Two", description = " Two", "2",image = R.drawable.eye_drops_svgrepo_com),
            ItemDataClass(id = 3, title = "Three", description = " Three", "3",image = R.drawable.magnetic_resonance_imaging_svgrepo_com),
            ItemDataClass(id = 4, title = "Four", description = " Four", "4",image = R.drawable.samples_svgrepo_com),
            ItemDataClass(id = 5, title = "Five", description = " Five", "5",image = R.drawable.strawberry_svgrepo_com)
        )
    }
}

