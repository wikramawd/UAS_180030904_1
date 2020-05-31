package com.bh183.withadharma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "db_gitar";
    private final static String TABLE_GITAR = "t_gitar";
    private final static String KEY_ID_GITAR = "ID_Gitar";
    private final static String KEY_NAMA = "Nama";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_CAPTION = "Caption";
    private final static String KEY_KETERANGAN = "Keterangan";
    private final static String KEY_LINK = "Link";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_GITAR = "CREATE TABLE " + TABLE_GITAR
                + "(" + KEY_ID_GITAR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAMA + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_GAMBAR + " TEXT, " + KEY_CAPTION + " TEXT, "
                + KEY_KETERANGAN + " TEXT, " + KEY_LINK + " TEXT);";

        db.execSQL(CREATE_TABLE_GITAR);
        inisialisasiGitar(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_GITAR;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public void tambahGitar(Gitar dataGitar){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataGitar.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataGitar.getTanggal()));
        cv.put(KEY_GAMBAR, dataGitar.getGambar());
        cv.put(KEY_CAPTION, dataGitar.getCaption());
        cv.put(KEY_KETERANGAN, dataGitar.getKeterangan());
        cv.put(KEY_LINK, dataGitar.getLink());

        db.insert(TABLE_GITAR, null, cv);
        db.close();
    }

    public void tambahGitar(Gitar dataGitar, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataGitar.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataGitar.getTanggal()));
        cv.put(KEY_GAMBAR, dataGitar.getGambar());
        cv.put(KEY_CAPTION, dataGitar.getCaption());
        cv.put(KEY_KETERANGAN, dataGitar.getKeterangan());
        cv.put(KEY_LINK, dataGitar.getLink());
        db.insert(TABLE_GITAR, null, cv);
    }

    public void editGitar(Gitar dataGitar){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataGitar.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataGitar.getTanggal()));
        cv.put(KEY_GAMBAR, dataGitar.getGambar());
        cv.put(KEY_CAPTION, dataGitar.getCaption());
        cv.put(KEY_KETERANGAN, dataGitar.getKeterangan());
        cv.put(KEY_LINK, dataGitar.getLink());

        db.update(TABLE_GITAR, cv, KEY_ID_GITAR +"=?", new String[]{String.valueOf(dataGitar.getIdGitar())});
        db.close();
    }

    public void hapusGitar(int idGitar){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GITAR, KEY_ID_GITAR + "=?", new String[]{String.valueOf(idGitar)});
        db.close();
    }

    public ArrayList<Gitar> getAllGitar(){
        ArrayList<Gitar> dataGitar = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_GITAR;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                } catch (ParseException er) {
                    er.printStackTrace();
                }

                Gitar tempGitar = new Gitar(
                        csr.getInt(0),
                        csr.getString(1),
                        tempDate,
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6)
                );

                dataGitar.add(tempGitar);
            } while (csr.moveToNext());
        }

        return dataGitar;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }


    private void inisialisasiGitar (SQLiteDatabase db) {
        int idGitar = 0;
        Date tempDate = new Date();

        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }



        Gitar gitar1 = new Gitar(
          idGitar,
                "Fender Player Telecaster in Polar White with maple FB",
                tempDate,
                storeImageFile(R.drawable.gitar1),
                "Bold, innovative and rugged, the Player Telecaster is pure Fender, through and through. The feel, the style and, most importantly, the sound—they’re all there, waiting for you to make them whisper or wail for your music. Versatile enough to handle almost anything you can create and durable enough to survive any gig, this workhorse is a trusty sidekick for your musical vision.",
                "• Body Shape: Telecaster®\n" +
                        "• Body Material: Alder\n" +
                        "• Body Color: Polar White\n" +
                        "• Body Finish: Gloss Polyester\n" +
                        "• Fretboard Material: Maple\n" +
                        "• Fretboard Radius: 9.5\" (241 mm)\n" +
                        "• Scale Length: 25.5\" (648 mm)\n" +
                        "• No. of Frets: 22\n" +
                        "• Fretboard Inlays: Black Dots\n" +
                        "• Pickup: Player Series Alnico 5 Tele® Single-Coil (Neck & Bridge)\n" +
                        "• Bridge: 6-Saddle String-Through-Body Tele® with Block Steel Saddles\n" +
                        "• Pickguard: 3-Ply Parchment\n" +
                        "• Strings: Fender® USA, NPS, (.009-.042 Gauges)\n" +
                        "• Tuning Machines: Standard Cast/Sealed\n" +
                        "• Hardware Finish: Nickel/Chrome\n" +
                        "• Case: None",
                "https://www.sweelee.com.my/products/fender-player-telecaster-electric-guitar-maple-fb-polar-white"
        );

        tambahGitar(gitar1, db);
        idGitar++;

        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Gitar gitar2= new Gitar(
                idGitar,
                "Squier Classic Vibe 60's Jazzmaster",
                tempDate,
                storeImageFile(R.drawable.gitar2),
                "The Classic Vibe ‘60s Jazzmaster® is a faithful and striking homage to the iconic Fender favourite, producing undeniable Jazzmaster tone courtesy of its dual Fender-Designed alnico single-coil pickups. Player-friendly features include a slim, comfortable “C”-shaped neck profile with an easy-playing 9.5”-radius fingerboard and narrow-tall frets, a vintage-style tremolo system for expressive string bending effects, and a floating bridge with barrel saddles for solid string stability. This throwback Squier model also features 1960s-inspired headstock markings, rich-looking nickel-plated hardware and a slick vintage-tint gloss neck finish for an old-school vibe.",
                "• Body Shape: Jazzmaster®\n" +
                        "• Body Colour: Olympic White\n" +
                        "• Body Material: Poplar\n" +
                        "• Body Finish: Gloss Polyurethane\n" +
                        "• Fretboard Material: Indian Laurel\n" +
                        "• Fretboard Radius: 9.5\" (241 mm)\n" +
                        "• Scale Length: 25.5\" (648 mm)\n" +
                        "• No. of Frets: 21\n" +
                        "• Fretboard Inlays: White Dots\n" +
                        "• Pickups: Fender® Designed Alnico Single-Coil\n" +
                        "• Bridge: 6-Saddle Vintage-Style with Non-Locking Floating Vibrato\n" +
                        "• Pickguard: 4-Ply Tortoiseshell\n" +
                        "• Strings: Nickel Plated Steel .009-.042 Gauges\n" +
                        "• Tuning Machines: Vintage-Style\n" +
                        "• Hardware Finish: Nickel",
                "https://www.sweelee.co.id/products/squier-classic-vibe-60s-jazzmaster-electric-guitar-laurel-fb-sonic-blue"
        );

        tambahGitar(gitar2, db);
        idGitar++;

        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Gitar gitar3 = new Gitar(
                idGitar,
                "Epiphone Les Paul Custom Pro Electric Guitar, RW Neck, Ebony",
                tempDate,
                storeImageFile(R.drawable.gitar3),
                "The Les Paul Custom made its debut in 1954 after the initial success of the Les Paul Goldtop. Les Paul himself suggested black as a great color for a new Les Paul because it looked \"classy\" and went well with a tuxedo on stage. From that point on, the Les Paul Custom has often been referred to as the \"tuxedo\" Les Paul. Now with Epiphone's superior ProBucker humbucker pickups with coil-splitting along with the Custom's classic gold hardware, fully bound body, neck and headstock, and pearloid inlays, we think you'll agree that the Epiphone Les Paul Custom PRO is dressed to kill.\n" +
                        "\n" +
                        "\n",
                "• Series/Shape: Les Paul\n" +
                        "• Binding: -Body Top (5-ply white/black) -Body Back (5-ply white/black) -Fingerboard (1-ply white) -Headstock (5-ply white/black) -Body(1-ply cream)\n" +
                        "• Body Wood: Mahogany\n" +
                        "• Bridge: LockTone\n" +
                        "• Fingerboard: Rosewood w/Pearloid Block\n" +
                        "• Fingerboard Radius: 12\"\n" +
                        "• Finish: Ebony\n" +
                        "• Frets: 22 Medium Jumbo\n" +
                        "• Hardware: Gold\n" +
                        "• Inlays: ProBucker-2/ -3\n" +
                        "• Model: Les Paul Custom Pro\n" +
                        "• Neck Shape: Slim Taper\n" +
                        "• Neck Wood: Mahogany\n" +
                        "• Nut Material: Graphtech NuBone™\n" +
                        "• Pickups: Neck/bridge pickup tone with push/pull; Phase switch\n" +
                        "• Scale Length: 24.75\"\n" +
                        "• Strings: D'Addario® 10, 13, 17, 26, 36, 46\n" +
                        "• Top Wood: Maple Veener\n" +
                        "• Nut Width: 1.68\"\n" +
                        "• Number Of Strings: 6 strings",
                "https://www.sweelee.co.id/products/epiphone-les-paul-custom-pro-electric-guitar-rw-neck-ebony"
        );

        tambahGitar(gitar3, db);
        idGitar++;

        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Gitar gitar4 = new Gitar(
                idGitar,
                "Squier Affinity Series Stratocaster Electric Guitar, Laurel FB, Competition Orange",
                tempDate,
                storeImageFile(R.drawable.gitar4),
                "The best value available today in electric guitar design, Squier Affinity Series guitars deliver solid sound with slick style. The Affinity Series Stratocaster combines the classic features that made the Stratocaster a rock 'n' roll favorite: sonic flexibility, authentic Fender sound, vintage style and eye-catching finishes.",
                "• Body Shape: Stratocaster\n" +
                        "• Body Material: Alder\n" +
                        "• Body Colour: Competition Orange\n" +
                        "• Body Finish: Polyurethane\n" +
                        "• Fretboard Material: Indian Laurel\n" +
                        "• Fretboard Radius: 9.5\" (241mm)\n" +
                        "• Scale Length: 25.5\" (648mm)\n" +
                        "• No. of Frets: 21\n" +
                        "• Fretboard Inlays: White Pearloid Dot\n" +
                        "• Pickup(s): Standard Single-Coil Strat\n" +
                        "• Bridge: 6-Saddle Vintage-Style Synchronized Tremolo\n" +
                        "• Pickguard: 3-Ply White\n" +
                        "• Strings: Fender® USA 250L Nickel Plated Steel (.009-.042 Gauges)\n" +
                        "• Tuning Machines: Standard Die-Cast\n" +
                        "• Hardware Finish: Chrome",
                "https://www.sweelee.com.my/products/squier-affinity-series-stratocaster-electric-guitar-laurel-fb-competition-orange"
        );

        tambahGitar(gitar4, db);
        idGitar++;

        try {
            tempDate = sdFormat.parse("13/03/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Gitar gitar5 = new Gitar(
                idGitar,
                "Fender Player Stratocaster Electric Guitar, Maple FB, Tidepool",
                tempDate,
                storeImageFile(R.drawable.gitar5),
                "The inspiring sound of a Stratocaster is one of the foundations of Fender. Featuring this classic sound—bell-like high end, punchy mids and robust low end, combined with crystal-clear articulation—the Player Stratocaster is packed with authentic Fender feel and style. It’s ready to serve your musical vision, it’s versatile enough to handle any style of music and it’s the perfect platform for creating your own sound.",
                "• Body Shape: Stratocaster®\n" +
                        "• Body Material: Alder\n" +
                        "• Body Color: Tidepool\n" +
                        "• Body Finish: Gloss Polyester\n" +
                        "• Fretboard Material: Maple\n" +
                        "• Fretboard Radius: 9.5\" (241 mm)\n" +
                        "• Scale Length: 25.5\" (648 mm)\n" +
                        "• No. of Frets: 22\n" +
                        "• Fretboard Inlays: Black Dots\n" +
                        "• Pickup: Player Series Alnico 5 Strat® Single-Coil (Neck, Middle & Bridge)\n" +
                        "• Bridge: 2-Point Synchronized Tremolo with Bent Steel Saddles\n" +
                        "• Pickguard: 3-Ply Parchment\n" +
                        "• Strings: Fender® USA, NPS, (.009-.042 Gauges)\n" +
                        "• Tuning Machines: Standard Cast/Sealed\n" +
                        "• Hardware Finish: Nickel/Chrome\n" +
                        "• Case: None",
                "https://www.sweelee.com.my/collections/featured-for-those-about-to-rock/products/fender-player-stratocaster-electric-guitar-maple-fb-tidepool"
        );

        tambahGitar(gitar5, db);

    }

}
