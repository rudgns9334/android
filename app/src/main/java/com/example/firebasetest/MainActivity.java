package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebasetest.RecommendList.calledAlready;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    ArrayList <String> stringList = new ArrayList<>();
    ListView listview;
    ArrayAdapter adapter;
    AutoSuggestAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.item);

        stringList.add("계란");
        stringList.add("전분");
        stringList.add("부침가루");
        stringList.add("소금");
        stringList.add("후추");
        stringList.add("참기름");
        stringList.add("밀가루");
        stringList.add("배추");
        stringList.add("호박");
        stringList.add("대파");
        stringList.add("다짐육");
        stringList.add("콩비지");
        stringList.add("다진마늘");
        stringList.add("통깨");
        stringList.add("마요네즈");
        stringList.add("설탕");
        stringList.add("식초");
        stringList.add("두부");
        stringList.add("김밥용김");
        stringList.add("어린잎채소");
        stringList.add("가지");
        stringList.add("당근");
        stringList.add("밥");
        stringList.add("오이");
        stringList.add("고추장");
        stringList.add("쪽파");
        stringList.add("중새우");
        stringList.add("양파");
        stringList.add("방울토마토");
        stringList.add("물");
        stringList.add("셀러리");
        stringList.add("햄");
        stringList.add("달걀");
        stringList.add("카레가루");
        stringList.add("식빵");
        stringList.add("꿀");
        stringList.add("달걀노른자");
        stringList.add("우유");
        stringList.add("고구마");
        stringList.add("건블루베리");
        stringList.add("들기름");
        stringList.add("버섯");
        stringList.add("당면");
        stringList.add("다짐육(소고기)");
        stringList.add("다짐육(돼지고기)");
        stringList.add("다진생강");
        stringList.add("간장");
        stringList.add("미나리");
        stringList.add("배추잎");
        stringList.add("감자");
        stringList.add("당근채");
        stringList.add("당근잎");
        stringList.add("고춧가루");
        stringList.add("무");
        stringList.add("단호박");
        stringList.add("된장");
        stringList.add("쌀뜨물");
        stringList.add("고등어");
        stringList.add("청양고추");
        stringList.add("홍고추");
        stringList.add("매실액");
        stringList.add("고추");
        stringList.add("라이스페이퍼");
        stringList.add("연어살");
        stringList.add("무채");
        stringList.add("고사리");
        stringList.add("도라지");
        stringList.add("콩나물");
        stringList.add("시금치");
        stringList.add("애호박");
        stringList.add("부침유");
        stringList.add("맛술");
        stringList.add("올리브유");
        stringList.add("단무지");
        stringList.add("아몬드");
        stringList.add("견과류");
        stringList.add("허브솔트");
        stringList.add("머드터드");
        stringList.add("황설탕");
        stringList.add("백오이");
        stringList.add("참치캔");
        stringList.add("삶은계란");
        stringList.add("다진고추");
        stringList.add("굴소스");
        stringList.add("다진식파");
        stringList.add("에멘탈 치즈가루");
        stringList.add("까나리액젓");
        stringList.add("깻잎");
        stringList.add("탄산수");
        stringList.add("얼음");
        stringList.add("허브(민트)");
        stringList.add("라임");
        stringList.add("슬라이스치즈");
        stringList.add("마요네스");
        stringList.add("슬라이스햄");
        stringList.add("모닝빵");
        stringList.add("토마토");
        stringList.add("허니머스타드");
        stringList.add("청고추");
        stringList.add("둥근호박");
        stringList.add("풋고추");
        stringList.add("쌈장");
        stringList.add("호박잎");
        stringList.add("와사비");
        stringList.add("오징어");
        stringList.add("쑥갓");
        stringList.add("치커리");
        stringList.add("상추");
        stringList.add("올리브오일");
        stringList.add("파마산치즈가루");
        stringList.add("토마토소스");
        stringList.add("파슬리가루");
        stringList.add("피자치즈");
        stringList.add("고구마잎");
        stringList.add("다진파");
        stringList.add("깨소금");
        stringList.add("노각");
        stringList.add("닭가슴살");
        stringList.add("미니새송이버섯");
        stringList.add("다진쪽파");
        stringList.add("바질");
        stringList.add("파뿌리");
        stringList.add("깐밤");
        stringList.add("노란 파프리카");
        stringList.add("붉은 파프리카");
        stringList.add("생강");
        stringList.add("고추가루");
        stringList.add("액젓");
        stringList.add("돛나물");
        stringList.add("부추");
        stringList.add("사과");
        stringList.add("소고기");
        stringList.add("무순");
        stringList.add("버터");
        stringList.add("잣");
        stringList.add("파마르산치즈");
        stringList.add("소금, 후추");
        stringList.add("화이트와인");
        stringList.add("새우");
        stringList.add("스파게티면");
        stringList.add("찹쌀가루");
        stringList.add("들깨가루");
        stringList.add("육수용 물");
        stringList.add("육수용 무");
        stringList.add("국물용 다시마");
        stringList.add("국물용멸치");
        stringList.add("육수용 대파");
        stringList.add("슈레드 치즈(모짜렐라치즈)");
        stringList.add("찐옥수수 알갱이");
        stringList.add("무지개고추");
        stringList.add("파프리카");
        stringList.add("낙지");
        stringList.add("절인 배추");
        stringList.add("감동젓");
        stringList.add("다진생각");
        stringList.add("소급");
        stringList.add("생굴");
        stringList.add("실파");
        stringList.add("밤");
        stringList.add("배");
        stringList.add("북어");
        stringList.add("대추");
        stringList.add("굴");
        stringList.add("낙지다리");
        stringList.add("갓");
        stringList.add("새우젓");
        stringList.add("마늘");
        stringList.add("돼지갈비");
        stringList.add("시래기");
        stringList.add("다진양파");
        stringList.add("사과즙");
        stringList.add("배즙");
        stringList.add("청주");
        stringList.add("다시마 우린 물");
        stringList.add("참쌀");
        stringList.add("인삼");
        stringList.add("땅콩");
        stringList.add("닭");
        stringList.add("통후추");
        stringList.add("볶은 흑임자(검은깨)");
        stringList.add("쇠고기(안심 또는 등심)");
        stringList.add("홍피망, 청피망 각각");
        stringList.add("양송이버섯");
        stringList.add("후추, 식용유");
        stringList.add("칠리");
        stringList.add("다진 마늘");
        stringList.add("월계수 잎");
        stringList.add("토마토페이스트");
        stringList.add("플레인요구르트");
        stringList.add("맛살");
        stringList.add("양배추");
        stringList.add("녹색피망");
        stringList.add("노랑/빨강 파프리카");
        stringList.add("브로코리 ");
        stringList.add("달걀 ");
        stringList.add("포도씨유");
        stringList.add("볶은소금");
        stringList.add("맛간장");
        stringList.add("새송이버섯");
        stringList.add("빨강파프리카·노랑 파프리카·청피망");
        stringList.add("굵은소금·후춧가루");
        stringList.add("양념장");
        stringList.add("소금약간");
        stringList.add("브로코리");
        stringList.add("진밥");
        stringList.add("적양배추");
        stringList.add("아가용 치즈");
        stringList.add("다시마국물");
        stringList.add("표고버섯");
        stringList.add("아기된장소스");
        stringList.add("멥쌀");
        stringList.add("찹쌀");
        stringList.add("진밥(멥쌀+찹쌀)");
        stringList.add("쇠고기");
        stringList.add("느타리버섯");
        stringList.add("흰살 생선");
        stringList.add("치즈");
        stringList.add("적당량의 물");
        stringList.add("비트");
        stringList.add("우유 적당량");
        stringList.add("대파 대");
        stringList.add("불린 쌀");
        stringList.add("청경채");
        stringList.add("정수물 또는 닭육수");
        stringList.add("양송이");
        stringList.add("아가용치즈");
        stringList.add("흑임자");
        stringList.add("표고");
        stringList.add("쌀");
        stringList.add("아욱");
        stringList.add("육수 및 생수");
        stringList.add("불린 찹쌀");
        stringList.add("불린 흑미");
        stringList.add("생수");
        stringList.add("쇠고기육수");
        stringList.add("불린 멥쌀");
        stringList.add("물(쇠고기육수)");
        stringList.add("정수물(쇠고기육수)");
        stringList.add("생수 반컵");
        stringList.add("생수 또는 분유물(모유)");
        stringList.add("애호박(속살)");
        stringList.add("비트 간 것");
        stringList.add("정제수(생수)");
        stringList.add("후춧가루");
        stringList.add("샐러리");
        stringList.add("완두콩");
        stringList.add("닭 육수");
        stringList.add("이태리고추");
        stringList.add("사라다나");
        stringList.add("트레비소");
        stringList.add("로즈마리");
        stringList.add("이태리파슬리");
        stringList.add("차이브");
        stringList.add("돼지 볼살");
        stringList.add("스파게티");
        stringList.add("계란노른자");
        stringList.add("그라나 빠다노");
        stringList.add("생크림");
        stringList.add("송어");
        stringList.add("양상추");
        stringList.add("파인애플");
        stringList.add("파슬리");
        stringList.add("잉어");
        stringList.add("[양념장] 고춧가루");
        stringList.add("물엿");
        stringList.add("메기");
        stringList.add("식용유");
        stringList.add("육수");
        stringList.add("냉면");
        stringList.add("샐러드채소");
        stringList.add("[배합초] 소금");
        stringList.add("[비빔양념] 간장");
        stringList.add("미소");
        stringList.add("팽이버섯");
        stringList.add("다시마");
        stringList.add("김치");
        stringList.add("꽁치");
        stringList.add("김칫국물");
        stringList.add("떡국떡");
        stringList.add("만두피");
        stringList.add("구운김");
        stringList.add("청정원맛선생");
        stringList.add("국간장");
        stringList.add("다진돼지고기");
        stringList.add("배추김치");
        stringList.add("숙주");
        stringList.add("파");
        stringList.add("미니파프리카");
        stringList.add("브로콜리");
        stringList.add("발효초");
        stringList.add("과일통조림");
        stringList.add("초콜릿");
        stringList.add("쫄면");
        stringList.add("새싹채소");
        stringList.add("우동면");
        stringList.add("김");
        stringList.add("[육수] 시판용장국");
        stringList.add("청정원어장");
        stringList.add("미더덕");
        stringList.add("바지락");
        stringList.add("잔새우");
        stringList.add("멸치");
        stringList.add("대하");
        stringList.add("꽃게");
        stringList.add("피망");
        stringList.add("청정원순창쌈장");
        stringList.add("날치알");
        stringList.add("베이컨");
        stringList.add("가쯔오브시");
        stringList.add("다시물");
        stringList.add("신김치");
        stringList.add("돼지고기안심");
        stringList.add("청정원국선생");
        stringList.add("홍합");
        stringList.add("모시조개");
        stringList.add("쭈꾸미");
        stringList.add("꽃빵");
        stringList.add("칵테일새우");
        stringList.add("참치통조림");
        stringList.add("불린쌀");
        stringList.add("호두");
        stringList.add("녹말가루");
        stringList.add("튀김가루");
        stringList.add("토마토케첩");
        stringList.add("고추기름");
        stringList.add("겨자잎");
        stringList.add("마른고추");
        stringList.add("허니머스터드");
        stringList.add("레몬즙");
        stringList.add("오렌지");
        stringList.add("절임무");
        stringList.add("게맛살");
        stringList.add("네모난햄");
        stringList.add("땅콩버터");
        stringList.add("돼지등갈비");
        stringList.add("레드와인");
        stringList.add("바베큐소스");
        stringList.add("[양념장] 다진파");
        stringList.add("청피망");
        stringList.add("홍피망");
        stringList.add("미소된장");
        stringList.add("가다랑이포");
        stringList.add("파스타");
        stringList.add("멥쌀가루");
        stringList.add("녹두");
        stringList.add("토란탕");
        stringList.add("닭고기");
        stringList.add("우엉");
        stringList.add("생강즙");
        stringList.add("시금치나물");
        stringList.add("도라지나물");
        stringList.add("고사리나물");
        stringList.add("고기산적");
        stringList.add("소스");
        stringList.add("소꼬리");
        stringList.add("사골");
        stringList.add("샐러드오일");
        stringList.add("아스파라거스");
        stringList.add("타임");
        stringList.add("전어");
        stringList.add("콩가루");
        stringList.add("전어젓갈");
        stringList.add("날콩가루");
        stringList.add("통파");
        stringList.add("닭발");
        stringList.add("서리태콩");
        stringList.add("소면");
        stringList.add("참깨");
        stringList.add("콩");
        stringList.add("깨");
        stringList.add("우무");
        stringList.add("메주콩");
        stringList.add("녹차국수");
        stringList.add("멸치액젓");
        stringList.add("죽순");
        stringList.add("[초고추장] 고추장");
        stringList.add("전복");
        stringList.add("저민마늘");
        stringList.add("레몬");
        stringList.add("찬밥");
        stringList.add("고운소금");
        stringList.add("굵은소금");
        stringList.add("붉은갓");
        stringList.add("삭힌고추");
        stringList.add("순창콩된장");
        stringList.add("근대");
        stringList.add("시럽");
        stringList.add("튀김기름");
        stringList.add("호박씨");
        stringList.add("건포도");
        stringList.add("엿기름");
        stringList.add("메주");
        stringList.add("숯");
        stringList.add("정종");
        stringList.add("열무");
        stringList.add("얼갈이배추");
        stringList.add("꽃소금");
        stringList.add("조청");
        stringList.add("쌀국수");
        stringList.add("통계피");
        stringList.add("피시소스");
        stringList.add("고수잎");
        stringList.add("[쇠고기육수] 쇠뼈");
        stringList.add("쇠꼬리");
        stringList.add("팔각");
        stringList.add("정향");
        stringList.add("간마늘");
        stringList.add("간생강");
        stringList.add("수삼");
        stringList.add("크림치즈");
        stringList.add("적채");
        stringList.add("무,래디쉬");
        stringList.add("잔멸치");
        stringList.add("석이버섯");
        stringList.add("겨자");
        stringList.add("갈치");
        stringList.add("굵은고춧가루");
        stringList.add("고운고춧가루");
        stringList.add("육회");
        stringList.add("계란후라이");
        stringList.add("취나물");
        stringList.add("무말랭이");
        stringList.add("쌈다시마");
        stringList.add("초고추장");
        stringList.add("돼지고기");
        stringList.add("다진쇠고기");
        stringList.add("봄동");
        stringList.add("영양부추");
        stringList.add("멸치다시물");
        stringList.add("통마늘");
        stringList.add("[절임간장] 진간장");
        stringList.add("대구살");
        stringList.add("양념");
        stringList.add("생밤");
        stringList.add("녹말");
        stringList.add("토마토케찹");
        stringList.add("채소");
        stringList.add("머스타드");
        stringList.add("케첩");
        stringList.add("생대구");
        stringList.add("가쓰오브시");
        stringList.add("미역");
        stringList.add("계핏가루");
        stringList.add("고춧기름");
        stringList.add("미림");
        stringList.add("채썬마늘");
        stringList.add("채썬생강");
        stringList.add("맛조개");
        stringList.add("곤약");
        stringList.add("폰즈소스");
        stringList.add("와사비소스");
        stringList.add("깨소스");
        stringList.add("치자");
        stringList.add("라면");
        stringList.add("꽃상추");
        stringList.add("청정원굴소스");
        stringList.add("참치");
        stringList.add("바게트");
        stringList.add("훈제연어");
        stringList.add("사워크림");
        stringList.add("체리알");
        stringList.add("머스터드");
        stringList.add("가래떡");
        stringList.add("어묵");
        stringList.add("해파리");
        stringList.add("골뱅이");
        stringList.add("진간장");
        stringList.add("빵가루");
        stringList.add("조기");
        stringList.add("조갯살");
        stringList.add("쌀가루");
        stringList.add("송이버섯");
        stringList.add("칼국수");
        stringList.add("우묵");
        stringList.add("흰콩");
        stringList.add("간수");
        stringList.add("두반장");
        stringList.add("녹말물");
        stringList.add("은행");
        stringList.add("스위트콘");
        stringList.add("옥수수통조림");
        stringList.add("핫소스");
        stringList.add("숙주나물");
        stringList.add("돈까스소스");
        stringList.add("우스터소스");
        stringList.add("바나나");
        stringList.add("타바스코");
        stringList.add("동태살");
        stringList.add("꼴뚜기");
        stringList.add("월계수잎");
        stringList.add("파마산치즈");
        stringList.add("장어");
        stringList.add("초생강");
        stringList.add("산초가루");
        stringList.add("장어뼈");
        stringList.add("장어대리");
        stringList.add("해초");
        stringList.add("흰후춧가루");
        stringList.add("맛살조개");
        stringList.add("조미술");
        stringList.add("가쓰오브시국물");
        stringList.add("메밀국수");
        stringList.add("쇠고기스톡");
        stringList.add("게살");
        stringList.add("흰살생선");
        stringList.add("미원");
        stringList.add("계란흰자");
        stringList.add("연어알");
        stringList.add("냉동만두");
        stringList.add("올리브");
        stringList.add("팥");
        stringList.add("수수");
        stringList.add("조");
        stringList.add("아보카도");
        stringList.add("박고지");
        stringList.add("오보로");
        stringList.add("페투치네");
        stringList.add("오레가노");
        stringList.add("흰후추");
        stringList.add("양겨자");
        stringList.add("일본된장");
        stringList.add("치즈가루");
        stringList.add("모짜렐라치즈");
        stringList.add("술");
        stringList.add("키위");
        stringList.add("다진홍고추");
        stringList.add("홀토마토");
        stringList.add("파래");
        stringList.add("토마토페스트");
        stringList.add("국멸치");
        stringList.add("들깻잎");
        stringList.add("케일");
        stringList.add("조림간장");
        stringList.add("보리");
        stringList.add("닭살");
        stringList.add("닭육수");
        stringList.add("닭다리");
        stringList.add("중국부추");
        stringList.add("기름");
        stringList.add("대구");
        stringList.add("옥수수");
        stringList.add("껍질콩");
        stringList.add("새우젓국");
        stringList.add("청장");
        stringList.add("오징어채");
        stringList.add("닭봉");
        stringList.add("잣가루");
        stringList.add("닭안심");
        stringList.add("고추냉이");
        stringList.add("청국장");
        stringList.add("해삼");
        stringList.add("떡");
        stringList.add("강력분");
        stringList.add("쑥");
        stringList.add("이스트");
        stringList.add("목이버섯");
        stringList.add("햇살담은간장");
        stringList.add("열무김치");
        stringList.add("프랑크소시지");
        stringList.add("흰떡");
        stringList.add("순대");
        stringList.add("들깻가루");
        stringList.add("코다리");
        stringList.add("크림수프");
        stringList.add("[멸치장국] 국멸치");
        stringList.add("채썬쇠고기");
        stringList.add("[불고기양념] 간장");
        stringList.add("김칫잎");
        stringList.add("조미료");
        stringList.add("춘권피");
        stringList.add("[쇠고기양념] 간장");
        stringList.add("꽁치통조림");
        stringList.add("다진대파");
        stringList.add("오이피클");
        stringList.add("체리");
        stringList.add("풋마늘");
        stringList.add("갑오징어");
        stringList.add("꼬치");
        stringList.add("완두콩통조림");
        stringList.add("양파즙");
        stringList.add("참나물");
        stringList.add("실고추");
        stringList.add("두릅");
        stringList.add("달래");
        stringList.add("고들빼기");
        stringList.add("시금치즙");
        stringList.add("당근즙");
        stringList.add("불린미역");
        stringList.add("유부");
        stringList.add("랩");
        stringList.add("맛소금");
        stringList.add("매실장아찌");
        stringList.add("검은깨");
        stringList.add("땅콩가루");
        stringList.add("겨자가루");
        stringList.add("피쉬소스");
        stringList.add("멸칫국물");
        stringList.add("사이다");
        stringList.add("인절미");
        stringList.add("우거지");
        stringList.add("액체육젓");
        stringList.add("[절임용 소금물] 소금");
        stringList.add("비엔나소시지");
        stringList.add("갈비");
        stringList.add("무즙");
        stringList.add("생태");
        stringList.add("콘플레이크");
        stringList.add("홍차티백");
        stringList.add("돼기고기");
        stringList.add("광어");
        stringList.add("피조개");
        stringList.add("청어알");
        stringList.add("초밥");
        stringList.add("배춧잎");
        stringList.add("조개살");
        stringList.add("오곡곡물");
        stringList.add("사우어크라우트");
        stringList.add("넛맥");
        stringList.add("포도");
        stringList.add("딸기");
        stringList.add("다진풋고추");
        stringList.add("녹차분말");
        stringList.add("다진청고추");
        stringList.add("말린표고버섯");
        stringList.add("얼음물");
        stringList.add("게");
        stringList.add("양");
        stringList.add("곱창");
        stringList.add("명란");
        stringList.add("생선살");
        stringList.add("새우살");
        stringList.add("생새우");
        stringList.add("콘프레이크");
        stringList.add("왜된장");
        stringList.add("맛국물");
        stringList.add("말린 표고버섯");
        stringList.add("팥삶은물");
        stringList.add("김치잎");
        stringList.add("근대잎");
        stringList.add("말린새우");
        stringList.add("통생강");
        stringList.add("참빛고운포도씨유");
        stringList.add("명란젓");
        stringList.add("육수용멸치");
        stringList.add("건새우가루");
        stringList.add("감자전분");
        stringList.add("곶감");
        stringList.add("쇠고기 육수");
        stringList.add("고구마줄기");
        stringList.add("삶은팥");
        stringList.add("슈가파우더");
        stringList.add("아몬드가루");
        stringList.add("찬물");
        stringList.add("적파프리카");
        stringList.add("황파프리카");
        stringList.add("누룽지");
        stringList.add("다진파슬리");
        stringList.add("[쇠고기양념] 다진파");
        stringList.add("토란");
        stringList.add("쇠고기(힘줄없는부위)");
        stringList.add("식용유/소금/참기름/잣가루");
        stringList.add("솔잎");
        stringList.add("풋콩");
        stringList.add("붉은 고추");
        stringList.add("드라이이스트");
        stringList.add("오렌지마멀레이드");
        stringList.add("아귀");
        stringList.add("파마산치즈치즈");
        stringList.add("레몬주스");
        stringList.add("레몬드레싱");
        stringList.add("케이퍼");
        stringList.add("트레비스");
        stringList.add("앤다이브");
        stringList.add("자몽");
        stringList.add("오렌지마요네즈");
        stringList.add("컬리플라워");
        stringList.add("요리술");
        stringList.add("들깨");
        stringList.add("피클");
        stringList.add("후루츠칵테일");
        stringList.add("은박컵");
        stringList.add("골뱅이통조림");
        stringList.add("마른새우");
        stringList.add("도토리묵");
        stringList.add("청포묵");
        stringList.add("가는파");
        stringList.add("굵은파");
        stringList.add("국수");
        stringList.add("통도라지");
        stringList.add("문어");
        stringList.add("장식용어묵");
        stringList.add("마늘종");
        stringList.add("마른오징어");
        stringList.add("꽈리고추");
        stringList.add("더덕");
        stringList.add("냉이");
        stringList.add("연근");
        stringList.add("[절임용소금물] 소금");
        stringList.add("[국물용 소금물] 소금");
        stringList.add("멸치젓");
        stringList.add("도가니");
        stringList.add("양지머리");
        stringList.add("밀국수");
        stringList.add("소 잡뼈");
        stringList.add("장조림");
        stringList.add("선지");
        stringList.add("아구");
        stringList.add("싸리버섯");
        stringList.add("배주머니");
        stringList.add("총각무");
        stringList.add("[절임용 소금물] 물");
        stringList.add("풋배추");
        stringList.add("통북어");
        stringList.add("동태");
        stringList.add("순두부");
        stringList.add("젓국");
        stringList.add("무명실");
        stringList.add("양배추잎");
        stringList.add("칼국수면");
        stringList.add("양념간장");
        stringList.add("상추잎");
        stringList.add("연겨자");
        stringList.add("적양배추잎");
        stringList.add("도미");
        stringList.add("블랙올리브");
        stringList.add("그린올리브");
        stringList.add("스위트마조람");
        stringList.add("재첩");
        stringList.add("삶은 문어살");
        stringList.add("중새우살");
        stringList.add("참소라살");
        stringList.add("가리비");
        stringList.add("검은껍질홍합");
        stringList.add("가재새우");
        stringList.add("루콜라");
        stringList.add("연어");
        stringList.add("페스토소스");
        stringList.add("모듬채소");
        stringList.add("동치미무");
        stringList.add("동치미국물");
        stringList.add("카레");
        stringList.add("청동호박");
        stringList.add("흑설탕");
        stringList.add("식물성기름");
        stringList.add("흰설탕");
        stringList.add("차조");
        stringList.add("안심");

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Refrigerator.db", null, 1);
        final DBHelper2 dbHelper2 = new DBHelper2(getApplicationContext(), "Recommend.db", null, 1);
        dbHelper.getResult(items);


        adapter2 = new AutoSuggestAdapter(this, android.R.layout.simple_list_item_1, stringList);
        autoComplete.setAdapter(adapter2);
        System.out.print("1" + stringList);
        autoComplete.setThreshold(1);
        listview = (ListView) this.findViewById(R.id.listview_ref);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, items) ;
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String strText = (String) parent.getItemAtPosition(position);
//            }
//        });
        // 테이블에 있는 모든 데이터 출력
        //final TextView result = findViewById(R.id.result);

        final EditText etItem = findViewById(R.id.item);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();



        Button insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                if(item.equals(""))
                    Toast.makeText(MainActivity.this, "재료를 입력하세요 !", Toast.LENGTH_SHORT).show();
                else if(dbHelper.isEqual(item))
                    Toast.makeText(MainActivity.this, "이미 냉장고 안에 있어요 !", Toast.LENGTH_SHORT).show();
                else {
                    dbHelper.insert(item);
                    items.add(item);
                    System.out.println(items);
                    adapter.notifyDataSetChanged();
                    Query mitem = dbR.child("material").child("data").orderByChild("IRDNT_NM").equalTo(item);
                    mitem.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot mquery: dataSnapshot.getChildren()){
                                final Integer R_id = mquery.child("RECIPE_ID").getValue(Integer.class);
                                if(dbHelper2.isEqual(R_id)) {
                                    dbHelper2.cntup(R_id);
                                }
                                else{
                                    Query recipy = dbR.child("recipy").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
                                    recipy.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for(DataSnapshot data : dataSnapshot.getChildren()) {
                                                Integer mcount = data.child("MATERIAL_CNT").getValue(Integer.class);
                                                dbHelper2.insert(R_id, mcount);
                                                dbHelper2.cntup(R_id);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                etItem.setText(null);
            }
        });


        // DB에 있는 데이터 삭제
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = "";
                SparseBooleanArray checkbox = listview.getCheckedItemPositions();

                int count = adapter.getCount() ;
                int check_cnt=0;
                for (int i = count-1; i >= 0; i--) {
                    if (checkbox.get(i)) {
                        item = items.get(i);
                        dbHelper.delete(item);
                        items.remove(i);
                        check_cnt++;
                        Query mitem = dbR.child("material").child("data").orderByChild("IRDNT_NM").equalTo(item);
                        mitem.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot mquery: dataSnapshot.getChildren()){
                                    Integer R_id = mquery.child("RECIPE_ID").getValue(Integer.class);
                                    dbHelper2.cntdown(R_id);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                if(check_cnt == 0)
                    Toast.makeText(MainActivity.this, "삭제할 재료를 선택해주세요 !", Toast.LENGTH_SHORT).show();
                listview.clearChoices() ;
                adapter.notifyDataSetChanged();

                etItem.setText(null);
            }
        });

        Button clean = findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String all = "ALL";
                dbHelper.delete("ALL");
                dbHelper2.delete(0);
                items.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

