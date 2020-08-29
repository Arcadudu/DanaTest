package ru.arcadudu.danatest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicPickerActivity extends AppCompatActivity {

    private List<Topic> topicList = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new TopicAdapter(topicList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);

        //////// пока просто попробуем с одиночными объектами

        ////////////////////////////////////////////////////////////////////////////////////////////
        String[] gbRu = {"остров", "острова", "средний", "температура", "выше", "тысяча",
                "Атлантический", "океан", "зона (район)", "километр", "городской (урбанистический)",
                "туман", "поверхность", "гора", "гористый", "озеро", "река", "климат", "осень"};

        List<String> words1 = Arrays.asList(gbRu);

        String[] gbEng = {"island", "islands", "average", "temperature", "above", "thousand",
                "Atlantic", "ocean", "area", "kilometer", "urban",
                "fog", "surface", "mountain", "mountainous", "lake", "river", "climate", "autumn"};

        List<String> translations1 = Arrays.asList(gbEng);

        ////////////////////////////////////////////////////////////////////////////////////////////
        String[] faceRu = {"лоб", "волосы", "бровь", "брови", "глаз",
                "глаза", "ресницы", "зрачок", "нос", "губы",
                "зуб", "зубы", "язык", "ухо", "уши", "рот"};

        List<String> words2 = Arrays.asList(faceRu);

        String[] faceEng = {"forehead", "hair", "brow", "brows", "eye",
                "eyes", "eyelashes", "pupil", "nose", "lips",
                "tooth", "teeth", "tongue", "ear", "ears", "mouth"};

        List<String> translations2 = Arrays.asList(faceEng);

        ////////////////////////////////////////////////////////////////////////////////////////////

        String[] bodyRu = {"голова", "шея", "плечо", "руки", "локоть", "предплечье", "кисть руки", "ладонь", "пальцы",
                "грудная клетка", "живот (желудок)", "бедро", "нога", "колено", "икра", "ступня", "ступни", "пальцы на ногах",
                "позвоночник", "спина", "попа"};

        List<String> words3 = Arrays.asList(bodyRu);

        String[] bodyEng = {"head", "neck", "shoulder", "arms", "elbow", "forearm", "hand", "palm", "fingers",
                "chest", "stomach", "hip", "leg", "knee", "calf", "foot", "feet", "toes", "spine", "back", "butt"};

        List<String> translations3 = Arrays.asList(bodyEng);

        ////////////////////////////////////////////////////////////////////////////////////////////

        String[] timeRu = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь",
                "ноябрь", "декабрь", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота", "воскресенье",
                "год", "месяц", "неделя", "день", "час", "минута", "секунда", "полночь", "полдень", "вечер", "утро"};

        List<String> words4 = Arrays.asList(timeRu);

        String[] timeEng = {"january", "february", "march", "april", "may", "june", "july", "august", "september",
                "october", "november", "december", "monday", "tu    esday", "wednesday", "thursday", "friday", "saturday", "sunday",
                "year", "month", "week", "day", "hour", "minute", "second", "midnight", "noon", "evening", "morning"};

        List<String> translations4 = Arrays.asList(timeEng);

        ////////////////////////////////////////////////////////////////////////////////////////////

        String[] houseBasicRu = {"кухня", "спальня", "столовая", "ванная", "туалет", "гостиная", "зал", "подвал", "чердак",
                "гараж", "балкон", "пол", "стена", "потолок", "дверь", "ворота", "окно", "этаж", "крыша",
                "задний двор", "сад", "лифт"};

        List<String> words5 = Arrays.asList(houseBasicRu);

        String[] houseBasicEng = {"kitchen", "bedroom", "dining.room", "bathroom", "toilet", "living.room", "hall", "basement", "attic",
                "garage", "balcony", "floor", "wall", "ceiling", "door", "gates", "window", "floor", "roof",
                "backyard", "garden", "elevator"};

        List<String> translations5 = Arrays.asList(houseBasicEng);

        topicList.add(new Topic("Великобритания", words1, translations1));
        topicList.add(new Topic("Лицо и его части", words2, translations2));
        topicList.add(new Topic("Части тела", words3, translations3));
        topicList.add(new Topic("Временные константы", words4, translations4));
        topicList.add(new Topic("Дом : базовый уровень", words5, translations5));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}