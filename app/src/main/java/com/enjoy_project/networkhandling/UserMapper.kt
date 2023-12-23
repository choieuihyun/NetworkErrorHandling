package com.enjoy_project.networkhandling


// Entity를 Model 데이터 클래스로 변환
// 원래 아키텍처 적용 시에 사용되지만 네트워크 매핑 하는김에 같이 해봄.
fun UserEntity.toModel() = UserModel(

    id = id,

    name = name,

    count =  count,

    team = team

)