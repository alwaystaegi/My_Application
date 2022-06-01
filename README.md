# hyotaek

+++++++++++++++++++2022.06.01 update++++++++++

파이어베이스를 통해서 회원가입&로그인까지 구현 
로그인 상태를 listactivity까지 넘기는 것은 아직 미구현(but 쉬워서 금방할것 같아요.)

현재 개발중: listactivity까지 로그인데이터 넘기기
            로그인데이터를 기반으로 게시글 작성하기.
+++++++++++++++++++++++++++++++++++++++++++++++

-현재 상태: 앱에 파이어베이스는 연동한 상태이지만 
연동만 되있고 데이터는 생성하지 못한 상태입니다.(user id라던지 게시글이라던지...)

또한 가능하면 저희가 srs에 작성한 상태로 만드는 게 좋을 것 같아서... 
원래 소스코드에 이미지를 추가하는 방법을 좀 찾아보고 있습니다.


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

안드로이드 스튜디오에서 다른 사람 프로젝트를 그대로 깃 클론하면 

prepareKotlinBuildScriptModel Task fails in a Java project이나

Task 'wrapper' not found in project ':app'.
같은 오류가 발생하는 것같아요


그럴땐 bulid.gradle에 

tasks.register("prepareKotlinBuildScriptModel"){}

task wrapper(type: Wrapper) {
        gradleVersion = '7.0'
    }
이 두코드를 아래 이미지처럼 입력하면 오류를 수정할 수 있습니다.
( sync한 이후에는 위 코드를 지워도 상관없어요)


![image](https://user-images.githubusercontent.com/50943860/170899081-5e9d5307-9626-4bd7-ad9e-28a25da6dfbb.png)

