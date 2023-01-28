import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.time.Year;
import java.util.Objects;

public class panel extends JFrame{
    private JPanel panel1;
    private JTextField ogrenci_ad;
    private JTextField ogrenci_soyad;
    private JButton kaydetButton;
    private JTextField father_name;
    private JTextField tc;
    private JTextField address;
    private JComboBox il_secme;
    private JComboBox ilce_secme;
    private DatePicker dg;
    private JButton photo_button;

    static final String DB_URL = "jdbc:mysql://localhost:3306/ogrenci_panel?useSSL=false";
    static final String USER = "kadir";
    static final String PASS = "";
//    static final String QUERY = "SELECT userID FROM users";

    public boolean isEmpty(String value) {
        return value.length() == 0;
    }

    public panel(){

        final String[] image_coded = new String[1];

        add(panel1);
        setSize(400, 600);
        setTitle("Öğrenci Kayıt");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[][] iller = {{"ADANA", "ALADAĞ", "CEYHAN", "ÇUKUROVA", "FEKE", "İMAMOĞLU", "KARAİSALI", "KARATAŞ", "KOZAN", "POZANTI", "SAİMBEYLİ", "SARIÇAM", "SEYHAN", "TUFANBEYLİ", "YUMURTALIK", "YÜREĞİR"}, {"ADIYAMAN", "BESNİ", "ÇELİKHAN", "GERGER", "GÖLBAŞI", "KAHTA", "MERKEZ", "SAMSAT", "SİNCİK", "TUT"}, {"AFYONKARAHİSAR", "BAŞMAKÇI", "BAYAT", "BOLVADİN", "ÇAY", "ÇOBANLAR", "DAZKIRI", "DİNAR", "EMİRDAĞ", "EVCİLER", "HOCALAR", "İHSANİYE", "İSCEHİSAR", "KIZILÖREN", "MERKEZ", "SANDIKLI", "SİNANPAŞA", "ŞUHUT", "SULTANDAĞI"}, {"AĞRI", "DİYADİN", "DOĞUBAYAZIT", "ELEŞKİRT", "HAMUR", "MERKEZ", "PATNOS", "TAŞLIÇAY", "TUTAK"}, {"AKSARAY", "AĞAÇÖREN", "ESKİL", "GÜLAĞAÇ", "GÜZELYURT", "MERKEZ", "ORTAKÖY", "SARIYAHŞİ", "SULTANHANI"}, {"AMASYA", "GÖYNÜCEK", "GÜMÜŞHACIKÖY", "HAMAMÖZÜ", "MERKEZ", "MERZİFON", "SULUOVA", "TAŞOVA"}, {"ANKARA", "AKYURT", "ALTINDAĞ", "AYAŞ", "BALA", "BEYPAZARI", "ÇAMLIDERE", "ÇANKAYA", "ÇUBUK", "ELMADAĞ", "ETİMESGUT", "EVREN", "GÖLBAŞI", "GÜDÜL", "HAYMANA", "KAHRAMANKAZAN", "KALECİK", "KEÇİÖREN", "KIZILCAHAMAM", "MAMAK", "NALLIHAN", "POLATLI", "PURSAKLAR", "ŞEREFLİKOÇHİSAR", "SİNCAN", "YENİMAHALLE"}, {"ANTALYA", "AKSEKİ", "AKSU", "ALANYA", "DEMRE", "DÖŞEMEALTI", "ELMALI", "FİNİKE", "GAZİPAŞA", "GÜNDOĞMUŞ", "İBRADI", "KAŞ", "KEMER", "KEPEZ", "KONYAALTI", "KORKUTELİ", "KUMLUCA", "MANAVGAT", "MURATPAŞA", "SERİK"}, {"ARDAHAN", "ÇILDIR", "DAMAL", "GÖLE", "HANAK", "MERKEZ", "POSOF"}, {"ARTVİN", "ARDANUÇ", "ARHAVİ", "BORÇKA", "HOPA", "KEMALPAŞA", "MERKEZ", "MURGUL", "ŞAVŞAT", "YUSUFELİ"}, {"AYDIN", "BOZDOĞAN", "BUHARKENT", "ÇİNE", "DİDİM", "EFELER", "GERMENCİK", "İNCİRLİOVA", "KARACASU", "KARPUZLU", "KOÇARLI", "KÖŞK", "KUŞADASI", "KUYUCAK", "NAZİLLİ", "SÖKE", "SULTANHİSAR", "YENİPAZAR"}, {"BALIKESİR", "ALTIEYLÜL", "AYVALIK", "BALYA", "BANDIRMA", "BİGADİÇ", "BURHANİYE", "DURSUNBEY", "EDREMİT", "ERDEK", "GÖMEÇ", "GÖNEN", "HAVRAN", "İVRİNDİ", "KARESİ", "KEPSUT", "MANYAS", "MARMARA", "SAVAŞTEPE", "SINDIRGI", "SUSURLUK"}, {"BARTIN", "AMASRA", "KURUCAŞİLE", "MERKEZ", "ULUS"}, {"BATMAN", "BEŞİRİ", "GERCÜŞ", "HASANKEYF", "KOZLUK", "MERKEZ", "SASON"}, {"BAYBURT", "AYDINTEPE", "DEMİRÖZÜ", "MERKEZ"}, {"BİLECİK", "BOZÜYÜK", "GÖLPAZARI", "İNHİSAR", "MERKEZ", "OSMANELİ", "PAZARYERİ", "SÖĞÜT", "YENİPAZAR"}, {"BİNGÖL", "ADAKLI", "GENÇ", "KARLIOVA", "KİĞI", "MERKEZ", "SOLHAN", "YAYLADERE", "YEDİSU"}, {"BİTLİS", "ADİLCEVAZ", "AHLAT", "GÜROYMAK", "HİZAN", "MERKEZ", "MUTKİ", "TATVAN"}, {"BOLU", "DÖRTDİVAN", "GEREDE", "GÖYNÜK", "KIBRISCIK", "MENGEN", "MERKEZ", "MUDURNU", "SEBEN", "YENİÇAĞA"}, {"BURDUR", "AĞLASUN", "ALTINYAYLA", "BUCAK", "ÇAVDIR", "ÇELTİKÇİ", "GÖLHİSAR", "KARAMANLI", "KEMER", "MERKEZ", "TEFENNİ", "YEŞİLOVA"}, {"BURSA", "BÜYÜKORHAN", "GEMLİK", "GÜRSU", "HARMANCIK", "İNEGÖL", "İZNİK", "KARACABEY", "KELES", "KESTEL", "M.KEMALPAŞA", "MUDANYA", "NİLÜFER", "ORHANELİ", "ORHANGAZİ", "OSMANGAZİ", "YENİŞEHİR", "YILDIRIM"}, {"ÇANAKKALE", "AYVACIK", "BAYRAMİÇ", "BİGA", "BOZCAADA", "ÇAN", "ECEABAT", "EZİNE", "GELİBOLU", "GÖKÇEADA", "LAPSEKİ", "MERKEZ", "YENİCE"}, {"ÇANKIRI", "ATKARACALAR", "BAYRAMÖREN", "ÇERKEŞ", "ELDİVAN", "ILGAZ", "KIZILIRMAK", "KORGUN", "KURŞUNLU", "MERKEZ", "ORTA", "ŞABANÖZÜ", "YAPRAKLI"}, {"ÇORUM", "ALACA", "BAYAT", "BOĞAZKALE", "DODURGA", "İSKİLİP", "KARGI", "LAÇİN", "MECİTÖZÜ", "MERKEZ", "OĞUZLAR", "ORTAKÖY", "OSMANCIK", "SUNGURLU", "UĞURLUDAĞ"}, {"DENİZLİ", "ACIPAYAM", "BABADAĞ", "BAKLAN", "BEKİLLİ", "BEYAĞAÇ", "BOZKURT", "BULDAN", "ÇAL", "ÇAMELİ", "ÇARDAK", "ÇİVRİL", "GÜNEY", "HONAZ", "KALE", "MERKEZEFENDİ", "PAMUKKALE", "SARAYKÖY", "SERİNHİSAR", "TAVAS"}, {"DİYARBAKIR", "BAĞLAR", "BİSMİL", "ÇERMİK", "ÇINAR", "ÇÜNGÜŞ", "DİCLE", "EĞİL", "ERGANİ", "HANİ", "HAZRO", "KAYAPINAR", "KOCAKÖY", "KULP", "LİCE", "SİLVAN", "SUR", "YENİŞEHİR"}, {"DÜZCE", "AKÇAKOCA", "ÇİLİMLİ", "CUMAYERİ", "GÖLYAKA", "GÜMÜŞOVA", "KAYNAŞLI", "MERKEZ", "YIĞILCA"}, {"EDİRNE", "ENEZ", "HAVSA", "İPSALA", "KEŞAN", "LALAPAŞA", "MERİÇ", "MERKEZ", "SÜLOĞLU", "UZUNKÖPRÜ"}, {"ELAZIĞ", "AĞIN", "ALACAKAYA", "ARICAK", "BASKİL", "KARAKOÇAN", "KEBAN", "KOVANCILAR", "MADEN", "MERKEZ", "PALU", "SİVRİCE"}, {"ERZİNCAN", "ÇAYIRLI", "İLİÇ", "KEMAH", "KEMALİYE", "MERKEZ", "OTLUKBELİ", "REFAHİYE", "TERCAN", "ÜZÜMLÜ"}, {"ERZURUM", "AŞKALE", "AZİZİYE", "ÇAT", "HINIS", "HORASAN", "İSPİR", "KARAÇOBAN", "KARAYAZI", "KÖPRÜKÖY", "NARMAN", "OLTU", "OLUR", "PALANDÖKEN", "PASİNLER", "PAZARYOLU", "ŞENKAYA", "TEKMAN", "TORTUM", "UZUNDERE", "YAKUTİYE"}, {"ESKİŞEHİR", "ALPU", "BEYLİKOVA", "ÇİFTELER", "GÜNYÜZÜ", "HAN", "İNÖNÜ", "MAHMUDİYE", "MİHALGAZİ", "MİHALIÇÇIK", "ODUNPAZARI", "SARICAKAYA", "SEYİTGAZİ", "SİVRİHİSAR", "TEPEBAŞI"}, {"GAZİANTEP", "ARABAN", "İSLAHİYE", "KARKAMIŞ", "NİZİP", "NURDAĞI", "OĞUZELİ", "ŞAHİNBEY", "ŞEHİTKAMİL", "YAVUZELİ"}, {"GİRESUN", "ALUCRA", "BULANCAK", "ÇAMOLUK", "ÇANAKÇI", "DERELİ", "DOĞANKENT", "ESPİYE", "EYNESİL", "GÖRELE", "GÜCE", "KEŞAP", "MERKEZ", "PİRAZİZ", "ŞEBİNKARAHİSAR", "TİREBOLU", "YAĞLIDERE"}, {"GÜMÜŞHANE", "KELKİT", "KÖSE", "KÜRTÜN", "MERKEZ", "ŞİRAN", "TORUL"}, {"HAKKARİ", "ÇUKURCA", "DERECİK", "MERKEZ", "ŞEMDİNLİ", "YÜKSEKOVA"}, {"HATAY", "ALTINÖZÜ", "ANTAKYA", "ARSUZ", "BELEN", "DEFNE", "DÖRTYOL", "ERZİN", "HASSA", "İSKENDERUN", "KIRIKHAN", "KUMLU", "PAYAS", "REYHANLI", "SAMANDAĞ", "YAYLADAĞI"}, {"IĞDIR", "ARALIK", "KARAKOYUNLU", "MERKEZ", "TUZLUCA"}, {"ISPARTA", "AKSU", "ATABEY", "EĞİRDİR", "GELENDOST", "GÖNEN", "KEÇİBORLU", "MERKEZ", "ŞARKİKARAAĞAÇ", "SENİRKENT", "SÜTÇÜLER", "ULUBORLU", "YALVAÇ", "YENİŞARBADEMLİ"}, {"İSTANBUL", "ADALAR", "ARNAVUTKÖY", "ATAŞEHİR", "AVCILAR", "BAĞCILAR", "BAHÇELİEVLER", "BAKIRKÖY", "BAŞAKŞEHİR", "BAYRAMPAŞA", "BEŞİKTAŞ", "BEYKOZ", "BEYLİKDÜZÜ", "BEYOĞLU", "BÜYÜKÇEKMECE", "ÇATALCA", "ÇEKMEKÖY", "ESENLER", "ESENYURT", "EYÜPSULTAN", "FATİH", "GAZİOSMANPAŞA", "GÜNGÖREN", "KADIKÖY", "KAĞITHANE", "KARTAL", "KÜÇÜKÇEKMECE", "MALTEPE", "PENDİK", "SANCAKTEPE", "SARIYER", "ŞİLE", "SİLİVRİ", "ŞİŞLİ", "SULTANBEYLİ", "SULTANGAZİ", "TUZLA", "ÜMRANİYE", "ÜSKÜDAR", "ZEYTİNBURNU"}, {"İZMİR", "ALİAĞA", "BALÇOVA", "BAYINDIR", "BAYRAKLI", "BERGAMA", "BEYDAĞ", "BORNOVA", "BUCA", "ÇEŞME", "ÇİĞLİ", "DİKİLİ", "FOÇA", "GAZİEMİR", "GÜZELBAHÇE", "KARABAĞLAR", "KARABURUN", "KARŞIYAKA", "KEMALPAŞA", "KINIK", "KİRAZ", "KONAK", "MENDERES", "MENEMEN", "NARLIDERE", "ÖDEMİŞ", "SEFERİHİSAR", "SELÇUK", "TİRE", "TORBALI", "URLA"}, {"KAHRAMANMARAŞ", "AFŞİN", "ANDIRIN", "ÇAĞLAYANCERİT", "DULKADİROĞLU", "EKİNÖZÜ", "ELBİSTAN", "GÖKSUN", "NURHAK", "ONİKİŞUBAT", "PAZARCIK", "TÜRKOĞLU"}, {"KARABÜK", "EFLANİ", "ESKİPAZAR", "MERKEZ", "OVACIK", "SAFRANBOLU", "YENİCE"}, {"KARAMAN", "AYRANCI", "BAŞYAYLA", "ERMENEK", "KAZIMKARABEKİR", "MERKEZ", "SARIVELİLER"}, {"KARS", "AKYAKA", "ARPAÇAY", "DİGOR", "KAĞIZMAN", "MERKEZ", "SARIKAMIŞ", "SELİM", "SUSUZ"}, {"KASTAMONU", "ABANA", "AĞLI", "ARAÇ", "AZDAVAY", "BOZKURT", "ÇATALZEYTİN", "CİDE", "DADAY", "DEVREKANİ", "DOĞANYURT", "HANÖNÜ", "İHSANGAZİ", "İNEBOLU", "KÜRE", "MERKEZ", "PINARBAŞI", "ŞENPAZAR", "SEYDİLER", "TAŞKÖPRÜ", "TOSYA"}, {"KAYSERİ", "AKKIŞLA", "BÜNYAN", "DEVELİ", "FELAHİYE", "HACILAR", "İNCESU", "KOCASİNAN", "MELİKGAZİ", "ÖZVATAN", "PINARBAŞI", "SARIOĞLAN", "SARIZ", "TALAS", "TOMARZA", "YAHYALI", "YEŞİLHİSAR"}, {"KİLİS", "ELBEYLİ", "MERKEZ", "MUSABEYLİ", "POLATELİ"}, {"KIRIKKALE", "BAHŞİLİ", "BALIŞEYH", "ÇELEBİ", "DELİCE", "KARAKEÇİLİ", "KESKİN", "MERKEZ", "SULAKYURT", "YAHŞİHAN"}, {"KIRKLARELİ", "BABAESKİ", "DEMİRKÖY", "KOFÇAZ", "LÜLEBURGAZ", "MERKEZ", "PEHLİVANKÖY", "PINARHİSAR", "VİZE"}, {"KIRŞEHİR", "AKÇAKENT", "AKPINAR", "BOZTEPE", "ÇİÇEKDAĞI", "KAMAN", "MERKEZ", "MUCUR"}, {"KOCAELİ", "BAŞİSKELE", "ÇAYIROVA", "DARICA", "DERİNCE", "DİLOVASI", "GEBZE", "GÖLCÜK", "İZMİT", "KANDIRA", "KARAMÜRSEL", "KARTEPE", "KÖRFEZ"}, {"KONYA", "AHIRLI", "AKÖREN", "AKŞEHİR", "ALTINEKİN", "BEYŞEHİR", "BOZKIR", "ÇELTİK", "CİHANBEYLİ", "ÇUMRA", "DERBENT", "DEREBUCAK", "DOĞANHİSAR", "EMİRGAZİ", "EREĞLİ", "GÜNEYSINIR", "HADİM", "HALKAPINAR", "HÜYÜK", "ILGIN", "KADINHANI", "KARAPINAR", "KARATAY", "KULU", "MERAM", "SARAYÖNÜ", "SELÇUKLU", "SEYDİŞEHİR", "TAŞKENT", "TUZLUKÇU", "YALIHÜYÜK", "YUNAK"}, {"KÜTAHYA", "ALTINTAŞ", "ASLANAPA", "ÇAVDARHİSAR", "DOMANİÇ", "DUMLUPINAR", "EMET", "GEDİZ", "HİSARCIK", "MERKEZ", "PAZARLAR", "ŞAPHANE", "SİMAV", "TAVŞANLI"}, {"MALATYA", "AKÇADAĞ", "ARAPGİR", "ARGUVAN", "BATTALGAZİ", "DARENDE", "DOĞANŞEHİR", "DOĞANYOL", "HEKİMHAN", "KALE", "KULUNCAK", "PÜTÜRGE", "YAZIHAN", "YEŞİLYURT"}, {"MANİSA", "AHMETLİ", "AKHİSAR", "ALAŞEHİR", "DEMİRCİ", "GÖLMARMARA", "GÖRDES", "KIRKAĞAÇ", "KÖPRÜBAŞI", "KULA", "SALİHLİ", "SARIGÖL", "SARUHANLI", "ŞEHZADELER", "SELENDİ", "SOMA", "TURGUTLU", "YUNUSEMRE"}, {"MARDİN", "ARTUKLU", "DARGEÇİT", "DERİK", "KIZILTEPE", "MAZIDAĞI", "MİDYAT", "NUSAYBİN", "ÖMERLİ", "SAVUR", "YEŞİLLİ"}, {"MERSİN", "AKDENİZ", "ANAMUR", "AYDINCIK", "BOZYAZI", "ÇAMLIYAYLA", "ERDEMLİ", "GÜLNAR", "MEZİTLİ", "MUT", "SİLİFKE", "TARSUS", "TOROSLAR", "YENİŞEHİR"}, {"MUĞLA", "BODRUM", "DALAMAN", "DATÇA", "FETHİYE", "KAVAKLIDERE", "KÖYCEĞİZ", "MARMARİS", "MENTEŞE", "MİLAS", "ORTACA", "SEYDİKEMER", "ULA", "YATAĞAN"}, {"MUŞ", "BULANIK", "HASKÖY", "KORKUT", "MALAZGİRT", "MERKEZ", "VARTO"}, {"NEVŞEHİR", "ACIGÖL", "AVANOS", "DERİNKUYU", "GÜLŞEHİR", "HACIBEKTAŞ", "KOZAKLI", "MERKEZ", "ÜRGÜP"}, {"NİĞDE", "ALTUNHİSAR", "BOR", "ÇAMARDI", "ÇİFTLİK", "MERKEZ", "ULUKIŞLA"}, {"ORDU", "AKKUŞ", "ALTINORDU", "AYBASTI", "ÇAMAŞ", "ÇATALPINAR", "ÇAYBAŞI", "FATSA", "GÖLKÖY", "GÜLYALI", "GÜRGENTEPE", "İKİZCE", "KABADÜZ", "KABATAŞ", "KORGAN", "KUMRU", "MESUDİYE", "PERŞEMBE", "ULUBEY", "ÜNYE"}, {"OSMANİYE", "BAHÇE", "DÜZİÇİ", "HASANBEYLİ", "KADİRLİ", "MERKEZ", "SUMBAS", "TOPRAKKALE"}, {"RİZE", "ARDEŞEN", "ÇAMLIHEMŞİN", "ÇAYELİ", "DEREPAZARI", "FINDIKLI", "GÜNEYSU", "HEMŞİN", "İKİZDERE", "İYİDERE", "KALKANDERE", "MERKEZ", "PAZAR"}, {"SAKARYA", "ADAPAZARI", "AKYAZI", "ARİFİYE", "ERENLER", "FERİZLİ", "GEYVE", "HENDEK", "KARAPÜRÇEK", "KARASU", "KAYNARCA", "KOCAALİ", "PAMUKOVA", "SAPANCA", "SERDİVAN", "SÖĞÜTLÜ", "TARAKLI"}, {"SAMSUN", "19MAYIS", "ALAÇAM", "ASARCIK", "ATAKUM", "AYVACIK", "BAFRA", "CANİK", "ÇARŞAMBA", "HAVZA", "İLKADIM", "KAVAK", "LADİK", "SALIPAZARI", "TEKKEKÖY", "TERME", "VEZİRKÖPRÜ", "YAKAKENT"}, {"ŞANLIURFA", "AKÇAKALE", "BİRECİK", "BOZOVA", "CEYLANPINAR", "EYYÜBİYE", "HALFETİ", "HALİLİYE", "HARRAN", "HİLVAN", "KARAKÖPRÜ", "SİVEREK", "SURUÇ", "VİRANŞEHİR"}, {"SİİRT", "BAYKAN", "ERUH", "KURTALAN", "MERKEZ", "PERVARİ", "ŞİRVAN", "TİLLO"}, {"SİNOP", "AYANCIK", "BOYABAT", "DİKMEN", "DURAĞAN", "ERFELEK", "GERZE", "MERKEZ", "SARAYDÜZÜ", "TÜRKELİ"}, {"ŞIRNAK", "BEYTÜŞŞEBAP", "CİZRE", "GÜÇLÜKONAK", "İDİL", "MERKEZ", "SİLOPİ", "ULUDERE"}, {"SİVAS", "AKINCILAR", "ALTINYAYLA", "DİVRİĞİ", "DOĞANŞAR", "GEMEREK", "GÖLOVA", "GÜRÜN", "HAFİK", "İMRANLI", "KANGAL", "KOYULHİSAR", "MERKEZ", "ŞARKIŞLA", "SUŞEHRİ", "ULAŞ", "YILDIZELİ", "ZARA"}, {"TEKİRDAĞ", "ÇERKEZKÖY", "ÇORLU", "ERGENE", "HAYRABOLU", "KAPAKLI", "MALKARA", "MARMARAEREĞLİSİ", "MURATLI", "SARAY", "ŞARKÖY", "SÜLEYMANPAŞA"}, {"TOKAT", "ALMUS", "ARTOVA", "BAŞÇİFTLİK", "ERBAA", "MERKEZ", "NİKSAR", "PAZAR", "REŞADİYE", "SULUSARAY", "TURHAL", "YEŞİLYURT", "ZİLE"}, {"TRABZON", "AKÇAABAT", "ARAKLI", "ARSİN", "BEŞİKDÜZÜ", "ÇARŞIBAŞI", "ÇAYKARA", "DERNEKPAZARI", "DÜZKÖY", "HAYRAT", "KÖPRÜBAŞI", "MAÇKA", "OF", "ORTAHİSAR", "ŞALPAZARI", "SÜRMENE", "TONYA", "VAKFIKEBİR", "YOMRA"}, {"TUNCELİ", "ÇEMİŞGEZEK", "HOZAT", "MAZGİRT", "MERKEZ", "NAZIMİYE", "OVACIK", "PERTEK", "PÜLÜMÜR"}, {"UŞAK", "BANAZ", "EŞME", "KARAHALLI", "MERKEZ", "SİVASLI", "ULUBEY"}, {"VAN", "BAHÇESARAY", "BAŞKALE", "ÇALDIRAN", "ÇATAK", "EDREMİT", "ERCİŞ", "GEVAŞ", "GÜRPINAR", "İPEKYOLU", "MURADİYE", "ÖZALP", "SARAY", "TUŞBA"}, {"YALOVA", "ALTINOVA", "ARMUTLU", "ÇİFTLİKKÖY", "ÇINARCIK", "MERKEZ", "TERMAL"}, {"YOZGAT", "AKDAĞMADENİ", "AYDINCIK", "BOĞAZLIYAN", "ÇANDIR", "ÇAYIRALAN", "ÇEKEREK", "KADIŞEHRİ", "MERKEZ", "SARAYKENT", "SARIKAYA", "ŞEFAATLİ", "SORGUN", "YENİFAKILI", "YERKÖY"}, {"ZONGULDAK", "ALAPLI", "ÇAYCUMA", "DEVREK", "EREĞLİ", "GÖKÇEBEY", "KİLİMLİ", "KOZLU", "MERKEZ"}};
        for (String[] strings : iller) {
            il_secme.addItem(new ComboItem(strings[0], strings[0]));
        }

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PreparedStatement preparedStatement = null;
                Connection c = null;

                String lastID = "0";
                ResultSet rs = null;

                try {

                    c = DriverManager.getConnection(DB_URL, USER, PASS);

                    preparedStatement = c.prepareStatement("SELECT id FROM ogrenciler ORDER BY id DESC LIMIT 1");
                    rs = preparedStatement.executeQuery();
                    rs.next();
                    lastID = String.valueOf(Integer.parseInt(rs.getString("id")) + 1);

                } catch (SQLException er) {
                    System.out.println("First record attempting...");
                }

                String ogn = String.valueOf(Year.now().getValue()).substring(String.valueOf(Year.now().getValue()).length()-2) + lastID ;
                String ad, soyad, ogrenci_no, dogum_tarihi, baba_adi, kimlik_no, adres, il, ilce, fotograf;
                ad = ogrenci_ad.getText();
                soyad = ogrenci_soyad.getText();
                ogrenci_no = ogn;
                dogum_tarihi =dg.getText();
                baba_adi = father_name.getText();
                kimlik_no = tc.getText();
                adres = address.getText();
                il = String.valueOf(il_secme.getSelectedItem());
                ilce = String.valueOf(ilce_secme.getSelectedItem());
                fotograf = image_coded[0];

                if(isEmpty(ad) || isEmpty(soyad) || isEmpty(ogrenci_no) || isEmpty(dogum_tarihi) || isEmpty(baba_adi) || isEmpty(kimlik_no) || isEmpty(adres) || isEmpty(il) || isEmpty(ilce)){
                    JOptionPane.showMessageDialog(null, "Lütfen öğrencinin bütün bilgilerini gözden geçiriniz. Boş bilgi bırakılamaz!");
                    return;
                }

                System.out.println(fotograf);

                Connection con = null;
                try {
                    con = DriverManager.getConnection(DB_URL, USER, PASS);
                    InputStream im = new FileInputStream(fotograf);
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO ogrenciler(ogrenci_id, ad, soyad, dogum_tarihi, baba_adi, kimlik, adres, il, ilce, fotograf) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, ogrenci_no);
                    pstmt.setString(2, ad);
                    pstmt.setString(3, soyad);
                    pstmt.setString(4, dogum_tarihi);
                    pstmt.setString(5, baba_adi);
                    pstmt.setString(6, kimlik_no);
                    pstmt.setString(7, adres);
                    pstmt.setString(8, il);
                    pstmt.setString(9, ilce);
                    pstmt.setBlob(10, im);
                    pstmt.execute();
                    JOptionPane.showMessageDialog(null, "Öğrenci kayıt edilmiştir! Öğrenci numarası: " + ogrenci_no);
                    ogn = "";
                    ogrenci_ad.setText("");
                    ogrenci_soyad.setText("");
                    dg.setText("");
                    father_name.setText("");
                    tc.setText("");
                    address.setText("");
                    im.reset();
                    image_coded[0] = "";
                } catch (SQLException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Öğrenci kayıt edilemedi! Sistemde kullanıcı bulunuyor olabilir!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        photo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(evt.getSource() == photo_button){
                    JFileChooser file_upload = new JFileChooser();
                    int res = file_upload.showOpenDialog(null);

                    if(res == JFileChooser.APPROVE_OPTION){
                        image_coded[0] = file_upload.getSelectedFile().getAbsolutePath();
                        photo_button.setText(file_upload.getSelectedFile().getName());
                    }
                }
            }
        });

        il_secme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(String.valueOf(il_secme.getSelectedItem()));
                ilce_secme.removeAllItems();
                for (String[] strings : iller) {
                    if(Objects.equals(strings[0], String.valueOf(il_secme.getSelectedItem()))){
                        for (int i = 1; i < strings.length; i++) {
                            ilce_secme.setEnabled(true);
                            ilce_secme.addItem(new ComboItem(strings[i], strings[i]));
                        }
                    }
                }
            }
        });
    }

}

