package tables
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Histories.schema ++ QuranIndonesia.schema ++ Surah.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Histories
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param user Database column user SqlType(VARCHAR), Length(199,true), Default()
   *  @param quranId Database column quran_id SqlType(INT)
   *  @param lastUpdated Database column last_updated SqlType(DATETIME), Default(None) */
  case class HistoriesRow(id: Int, user: String = "", quranId: Int, lastUpdated: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching HistoriesRow objects using plain SQL queries */
  implicit def GetResultHistoriesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[HistoriesRow] = GR{
    prs => import prs._
    HistoriesRow.tupled((<<[Int], <<[String], <<[Int], <<?[java.sql.Timestamp]))
  }
  /** Table description of table histories. Objects of this class serve as prototypes for rows in queries. */
  class Histories(_tableTag: Tag) extends Table[HistoriesRow](_tableTag, "histories") {
    def * = (id, user, quranId, lastUpdated) <> (HistoriesRow.tupled, HistoriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(user), Rep.Some(quranId), lastUpdated).shaped.<>({r=>import r._; _1.map(_=> HistoriesRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user SqlType(VARCHAR), Length(199,true), Default() */
    val user: Rep[String] = column[String]("user", O.Length(199,varying=true), O.Default(""))
    /** Database column quran_id SqlType(INT) */
    val quranId: Rep[Int] = column[Int]("quran_id")
    /** Database column last_updated SqlType(DATETIME), Default(None) */
    val lastUpdated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_updated", O.Default(None))
  }
  /** Collection-like TableQuery object for table Histories */
  lazy val Histories = new TableQuery(tag => new Histories(tag))

  /** Entity class storing rows of table QuranIndonesia
   *  @param id Database column ID SqlType(INT), AutoInc, PrimaryKey
   *  @param databaseid Database column DatabaseID SqlType(SMALLINT)
   *  @param suraid Database column SuraID SqlType(INT)
   *  @param verseid Database column VerseID SqlType(INT)
   *  @param ayahtext Database column AyahText SqlType(TEXT)
   *  @param ayaharab Database column AyahArab SqlType(TEXT), Default(None)
   *  @param ayahjassin Database column AyahJassin SqlType(TEXT), Default(None) */
  case class QuranIndonesiaRow(id: Int, databaseid: Short, suraid: Int, verseid: Int, ayahtext: String, ayaharab: Option[String] = None, ayahjassin: Option[String] = None)
  /** GetResult implicit for fetching QuranIndonesiaRow objects using plain SQL queries */
  implicit def GetResultQuranIndonesiaRow(implicit e0: GR[Int], e1: GR[Short], e2: GR[String], e3: GR[Option[String]]): GR[QuranIndonesiaRow] = GR{
    prs => import prs._
    QuranIndonesiaRow.tupled((<<[Int], <<[Short], <<[Int], <<[Int], <<[String], <<?[String], <<?[String]))
  }
  /** Table description of table quran_indonesia. Objects of this class serve as prototypes for rows in queries. */
  class QuranIndonesia(_tableTag: Tag) extends Table[QuranIndonesiaRow](_tableTag, "quran_indonesia") {
    def * = (id, databaseid, suraid, verseid, ayahtext, ayaharab, ayahjassin) <> (QuranIndonesiaRow.tupled, QuranIndonesiaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(databaseid), Rep.Some(suraid), Rep.Some(verseid), Rep.Some(ayahtext), ayaharab, ayahjassin).shaped.<>({r=>import r._; _1.map(_=> QuranIndonesiaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column DatabaseID SqlType(SMALLINT) */
    val databaseid: Rep[Short] = column[Short]("DatabaseID")
    /** Database column SuraID SqlType(INT) */
    val suraid: Rep[Int] = column[Int]("SuraID")
    /** Database column VerseID SqlType(INT) */
    val verseid: Rep[Int] = column[Int]("VerseID")
    /** Database column AyahText SqlType(TEXT) */
    val ayahtext: Rep[String] = column[String]("AyahText")
    /** Database column AyahArab SqlType(TEXT), Default(None) */
    val ayaharab: Rep[Option[String]] = column[Option[String]]("AyahArab", O.Default(None))
    /** Database column AyahJassin SqlType(TEXT), Default(None) */
    val ayahjassin: Rep[Option[String]] = column[Option[String]]("AyahJassin", O.Default(None))
  }
  /** Collection-like TableQuery object for table QuranIndonesia */
  lazy val QuranIndonesia = new TableQuery(tag => new QuranIndonesia(tag))

  /** Entity class storing rows of table Surah
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param nameSurah Database column name_surah SqlType(VARCHAR), Length(199,true), Default(None)
   *  @param arab Database column arab SqlType(VARCHAR), Length(199,true), Default(None)
   *  @param indonesia Database column indonesia SqlType(VARCHAR), Length(199,true), Default(None)
   *  @param numOfSurah Database column num_of_surah SqlType(INT), Default(None)
   *  @param place Database column place SqlType(VARCHAR), Length(99,true), Default(None)
   *  @param seqRevelation Database column seq_revelation SqlType(INT), Default(None) */
  case class SurahRow(id: Int, nameSurah: Option[String] = None, arab: Option[String] = None, indonesia: Option[String] = None, numOfSurah: Option[Int] = None, place: Option[String] = None, seqRevelation: Option[Int] = None)
  /** GetResult implicit for fetching SurahRow objects using plain SQL queries */
  implicit def GetResultSurahRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[SurahRow] = GR{
    prs => import prs._
    SurahRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table surah. Objects of this class serve as prototypes for rows in queries. */
  class Surah(_tableTag: Tag) extends Table[SurahRow](_tableTag, "surah") {
    def * = (id, nameSurah, arab, indonesia, numOfSurah, place, seqRevelation) <> (SurahRow.tupled, SurahRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), nameSurah, arab, indonesia, numOfSurah, place, seqRevelation).shaped.<>({r=>import r._; _1.map(_=> SurahRow.tupled((_1.get, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name_surah SqlType(VARCHAR), Length(199,true), Default(None) */
    val nameSurah: Rep[Option[String]] = column[Option[String]]("name_surah", O.Length(199,varying=true), O.Default(None))
    /** Database column arab SqlType(VARCHAR), Length(199,true), Default(None) */
    val arab: Rep[Option[String]] = column[Option[String]]("arab", O.Length(199,varying=true), O.Default(None))
    /** Database column indonesia SqlType(VARCHAR), Length(199,true), Default(None) */
    val indonesia: Rep[Option[String]] = column[Option[String]]("indonesia", O.Length(199,varying=true), O.Default(None))
    /** Database column num_of_surah SqlType(INT), Default(None) */
    val numOfSurah: Rep[Option[Int]] = column[Option[Int]]("num_of_surah", O.Default(None))
    /** Database column place SqlType(VARCHAR), Length(99,true), Default(None) */
    val place: Rep[Option[String]] = column[Option[String]]("place", O.Length(99,varying=true), O.Default(None))
    /** Database column seq_revelation SqlType(INT), Default(None) */
    val seqRevelation: Rep[Option[Int]] = column[Option[Int]]("seq_revelation", O.Default(None))
  }
  /** Collection-like TableQuery object for table Surah */
  lazy val Surah = new TableQuery(tag => new Surah(tag))
}
