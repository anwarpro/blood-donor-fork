package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {
    TextView rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().setTitle("F.A.Q.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rules = findViewById(R.id.txtrules);
        rules.setText("\t\n" +
                "Eligibility to Donate Blood\n" +
                "Basic Requirements\n" +
                "\n" +
                "Be at least 17 years old in most states\n" +
                "Weight: At least 45 kg (100 Ibs) for both males and females\n" +
                "Well Being: Feeling well that day. Not having colds, coughs or flu in the last one week. No fever (Temperature >37.5°C) in the last 3 weeks.\n" +
                "Major Illness/Surgery\n" +
                "\n\nPersons with the following conditions are not eligible for blood donation:\n" +
                "\n" +
                "Diseases of the heart or lungs (Donors who are asthmatic and without symptoms of asthma is eligible)\n" +
                "High blood pressure on medication\n" +
                "Diabetes on medication\n" +
                "Major surgery (can donate after 12 months)\n" +
                "AIDS or symptoms of AIDS, such as unexplained fevers, severe night sweats, unexpected weight loss, swollen glands, chronic diarrhoea or rare cancer\n" +
                "Epilepsy\n" +
                "Hepatitis B or C\n" +
                "Syphilis\n" +
                "Some Common Reasons For Temporary Deferral\n" +
                "Cold, flu or sore throat, please wait one week after recovery or treatment\n" +
                "Tooth extraction or dental work, please wait 3 days after treatment\n" +
                "Skin infections (minor), wait 1 week after complete healing\n" +
                "Normal pregnancy, please wait 6 weeks after delivery and when you are not breast-feeding.\n" +
                "Travelled to a malaria endemic area such as rural areas in Malaysia, Thailand, Indonesia, Philippines, India, etc, please wait 6 weeks to 3 years and ask the attending doctor/nurse whether you are eligible for donation\n" +
                "Close contact with Hepatitis B, wait 12 months and after full course of hepatitis B vaccination (and shown a satisfactory antibody response)\n" +
                "Infectious Diseases e.g. Chickenpox, Measles, Dengue, wait 6 months after recovery\n" +
                "Medication\n" +
                "People who are taking drugs for cancer treatment, heart diseases, high blood pressure, diabetes or current infections will not be accepted as blood donors.\n" +
                "\n" +
                "For those taking:\n" +
                "\n" +
                "Traditional Medication\tPlease wait 3 days\n" +
                "Antibiotics\tPlease wait 1 week\n" +
                "Anti-malaria Medication\tPlease wait 4 weeks\n");
    }
}
