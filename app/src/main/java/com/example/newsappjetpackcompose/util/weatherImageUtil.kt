package com.example.newsappjetpackcompose.util

import com.example.newsappjetpackcompose.model.weatherModel.Condition

fun getImageURLFromCondition(condition: Condition, isDay: Boolean): String {
    return if (condition.code == 1000 && isDay) {
        "https://images.unsplash.com/photo-1533324268742-60b233802eef?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80";
    } else if (condition.code == 1000 && !isDay) {
        "https://images.unsplash.com/photo-1618244627800-aa353e26161c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"

    }else if(condition.code==1003 && isDay){
        "https://images.unsplash.com/photo-1445297983845-454043d4eef4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
    }
    else if(condition.code==1003 && !isDay){
        "https://images.unsplash.com/photo-1532767153582-b1a0e5145009?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1006|| condition.code==1009 && !isDay){
        "https://images.unsplash.com/photo-1533983272060-edb6f56af634?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1323&q=80"
    }
    else if(condition.code==1006 || condition.code==1009 && isDay){
        "https://images.unsplash.com/photo-1604605801364-3e05c0b4b77c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1030 && isDay){
        "https://images.unsplash.com/photo-1573382032748-2d1b42c6c812?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1030 && !isDay){
        "https://images.unsplash.com/photo-1515693516428-3c89b92d3220?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1135 || condition.code== 1147){
        "https://images.unsplash.com/photo-1585651686997-5516bd534e9d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1183 || condition.code==1180 && isDay){
        "https://cdn.pixabay.com/photo/2019/10/30/21/46/gods-gift-4590624_1280.jpg"
    }
    else if(condition.code==1183 || condition.code==1180 && !isDay){
        "https://media.istockphoto.com/id/1390273113/photo/abstract-background-blurred-city-lights-and-raindrops-on-glass.webp?b=1&s=612x612&w=0&k=20&c=x8bWHrfyeaFiwn9Lrhts_vOqljiM3uJjqzrurc9DQ5I="
    }
    else if(condition.code==1186 || condition.code==1189 && isDay){
        "https://images.unsplash.com/photo-1547932133-a791d9e71030?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
    }
    else if(condition.code==1186 || condition.code==1189 && !isDay){
        "https://images.unsplash.com/photo-1624608488840-cdb29f031ce9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
    }
    else if(condition.code==1192 || condition.code==1195 && isDay){
       "https://cdn.pixabay.com/photo/2018/05/07/06/24/umbrella-3380192_1280.jpg"
    }
    else if(condition.code==1192 || condition.code==1195 && !isDay){
"https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8aGVhdnklMjByYWlufGVufDB8fDB8fHww&auto=format&fit=crop&w=600&q=60"
    }
    else {
        ""
    }
}