conn = new Mongo();
db = conn.getDB(databaseName);
db.getSiblingDB(databaseName);

db.system.js.insertOne({
_id: "getUnreadedMessagesCountForUser",
value : function (channel, user) {
        if(channel.messages === undefined) {
            return 0;
        }
        return db.getCollection("messageChannel").findOne({"_id": ObjectId(channel)}).messages
        .filter(function (value) { if(value.author === user || (value.readedBy &&
        value.readedBy.length>0 &&
        value.readedBy.find(function(el) { return el===user }) != undefined )) {
          return false;
        } else {
          return true;
        }}).length
    }
})

db.createCollection("icd10_level1_dictionaries");
db.icd10_level1_dictionaries.deleteMany({});
db.icd10_level1_dictionaries.insertMany([
{ 
	"name": "Certain infectious and parasitic diseases", 
	"values": 
	[
		"A00","A01","A02","A03","A04","A05","A06","A07","A08","A09","A10","A11","A12","A13","A14","A15","A16","A17","A18","A19","A20","A21","A22","A23","A24","A25",
		"A26","A27","A28","A29","A30","A31","A32","A33","A34","A35","A36","A37","A38","A39","A40","A41","A42","A43","A44","A45","A46","A47","A48","A49","A50","A51",
		"A52","A53","A54","A55","A56","A57","A58","A59","A60","A61","A62","A63","A64","A65","A66","A67","A68","A69","A70","A71","A72","A73","A74","A75","A76","A77",
		"A78","A79","A80","A81","A82","A83","A84","A85","A86","A87","A88","A89","A90","A91","A92","A93","A94","A95","A96","A97","A98","A99",
		"B00","B01","B02","B03","B04","B05","B06","B07","B08","B09","B10","B11","B12","B13","B14","B15","B16","B17","B18","B19","B20","B21","B22","B23","B24","B25",
		"B26","B27","B28","B29","B30","B31","B32","B33","B34","B35","B36","B37","B38","B39","B40","B41","B42","B43","B44","B45","B46","B47","B48","B49","B50","B51",
		"B52","B53","B54","B55","B56","B57","B58","B59","B60","B61","B62","B63","B64","B65","B66","B67","B68","B69","B70","B71","B72","B73","B74","B75","B76","B77",
		"B78","B79","B80","B81","B82","B83","B84","B85","B86","B87","B88","B89","B90","B91","B92","B93","B94","B95","B96","B97","B98","B99"
	] 
},
{ 
	"name": "Neoplasms", 
	"values": 
	[
		"C00","C01","C02","C03","C04","C05","C06","C07","C08","C09","C10","C11","C12","C13","C14","C15","C16","C17","C18","C19","C20","C21","C22","C23","C24","C25",
		"C26","C27","C28","C29","C30","C31","C32","C33","C34","C35","C36","C37","C38","C39","C40","C41","C42","C43","C44","C45","C46","C47","C48","C49","C50","C51",
		"C52","C53","C54","C55","C56","C57","C58","C59","C60","C61","C62","C63","C64","C65","C66","C67","C68","C69","C70","C71","C72","C73","C74","C75","C76","C77",
		"C78","C79","C80","C81","C82","C83","C84","C85","C86","C87","C88","C89","C90","C91","C92","C93","C94","C95","C96","C97",
		"D00","D01","D02","D03","D04","D05","D06","D07","D08","D09","D10","D11","D12","D13","D14","D15","D16","D17","D18","D19","D20","D21","D22","D23","D24","D25",
		"D26","D27","D28","D29","D30","D31","D32","D33","D34","D35","D36","D37","D38","D39","D40","D41","D42","D43","D44","D45","D46","D47","D48"
	] 
},
{ 
	"name": "Diseases of the blood and blood-forming organs", 
	"values": 
	[
		"D50","D51","D52","D53","D55","D56","D57","D58","D59","D60","D61","D62","D63","D64","D65","D66","D67","D68","D69","D70","D71","D72","D73","D74","D75","D76",
		"D77","D80","D81","D82","D83","D84","D85","D86","D87","D88","D89"
	] 
},
{ 
	"name": "Endocrine, nutritional and metabolic diseases", 
	"values": 
	[
		"E00","E01","E02","E03","E04","E05","E06","E07","E10","E11","E12","E13","E14","E15","E16","E20","E21","E22","E23","E24","E25",
		"E26","E27","E28","E29","E30","E31","E32","E33","E34","E35","E40","E41","E42","E43","E44","E45","E46","E50","E51","E52","E53",
		"E54","E55","E56","E57","E58","E59","E60","E61","E62","E63","E64","E65","E66","E67","E68","E70","E71","E72","E73","E74","E75",
		"E76","E77","E78","E79","E80","E81","E82","E83","E84","E85","E86","E87","E88","E89","E90"
	] 
},
{ 
	"name": "Mental and behavioural disorders", 
	"values": 
	[
		"F00","F01","F02","F03","F04","F05","F06","F07","F08","F09","F10","F11","F12","F13","F14","F15","F16","F17","F18","F19","F20","F21","F22","F23","F24","F25",
		"F26","F27","F28","F29","F30","F31","F32","F33","F34","F35","F36","F37","F38","F39","F40","F41","F42","F43","F44","F45","F46","F47","F48","F50","F51","F52",
		"F53","F54","F55","F56","F57","F58","F59","F60","F61","F62","F63","F64","F65","F66","F67","F68","F69","F70","F71","F72","F73","F74","F75","F76","F77","F78",
		"F79","F80","F81","F82","F83","F84","F85","F86","F87","F88","F89","F90","F91","F92","F93","F94","F95","F96","F97","F98","F99"
	] 
},
{ 
	"name": "Diseases of the nervous system", 
	"values": 
	[
		"G00","G01","G02","G03","G04","G05","G06","G07","G08","G09","G10","G11","G12","G13","G14","G20","G21","G22","G23","G24","G25","G26","G30","G31","G32",
		"G35","G36","G37","G40","G41","G42","G43","G44","G45","G46","G47","G50","G51","G52","G53","G54","G55","G56","G57","G58","G59","G60","G61","G62","G63",
		"G64","G70","G71","G72","G73","G80","G81","G82","G83","G90","G91","G92","G93","G94","G95","G96","G97","G98","G99"
	] 
},
{
	"name": "Diseases of the eye and adnexa", 
	"values": 
	[
		"H00","H01","H02","H03","H04","H05","H06","H10","H11","H12","H13","H15","H16","H17","H18","H19","H20","H21","H22","H25","H26","H27","H28","H30","H31","H32",
		"H33","H34","H35","H36","H40","H41","H42","H43","H44","H45","H46","H47","H48","H49","H50","H51","H52","H53","H54","H55","H56","H57","H58","H59"
	] 
},
{
	"name": "Diseases of the ear and mastoid process", 
	"values": 
	[
		"H60","H61","H62","H65","H66","H67","H68","H69","H70","H71","H72","H73","H74","H75","H80","H81","H82","H83","H90","H91","H92","H93","H94","H95"
	] 
},
{
	"name": "Diseases of the circulatory system", 
	"values": 
	[
		"I00","I01","I02","I05","I06","I07","I08","I09","I10","I11","I12","I13","I14","I15","I20","I21","I22","I23","I24","I25","I26","I27","I28","I30","I31","I32","I33","I34","I35","I36","I37",
		"I38","I39","I40","I41","I42","I43","I44","I45","I46","I47","I48","I49","I50","I51","I52","I60","I61","I62","I63","I64","I65","I66","I67","I68","I69","I70","I71","I72","I73","I74","I75",
		"I76","I77","I78","I79","I80","I81","I82","I83","I84","I85","I86","I87","I88","I89","I95","I96","I97","I98","I99"
	] 
},
{
	"name": "Diseases of the respiratory system", 
	"values": 
	[
		"J00","J01","J02","J03","J04","J05","J06","J09","J10","J11","J12","J13","J14","J15","J16","J17","J18","J20","J21","J22","J30","J31","J32","J33","J34","J35","J36","J37",
		"J38","J39","J40","J41","J42","J43","J44","J45","J46","J47","J60","J61","J62","J63","J64","J65","J66","J67","J68","J69","J70","J80","J81","J82","J83","J84","J85","J86",
		"J90","J91","J92","J93","J94","J95","J96","J97","J98","J99"
	] 
},
{
	"name": "Diseases of the digestive system", 
	"values": 
	[
		"K00","K01","K02","K03","K04","K05","K06","K07","K08","K09","K10","K11","K12","K13","K14","K20","K21","K22","K23","K24","K25","K26","K27","K28","K29","K30",
		"K31","K35","K36","K37","K38","K40","K41","K42","K43","K44","K45","K46","K50","K51","K52","K55","K56","K57","K58","K59","K60","K61","K62","K63","K64","K65",
		"K66","K67","K70","K71","K72","K73","K74","K75","K76","K77","K80","K81","K82","K83","K84","K85","K86","K87","K90","K91","K92","K93"
	] 
},
{
	"name": "Diseases of the skin and subcutaneous tissue", 
	"values": 
	[
		"L00","L01","L02","L03","L04","L05","L06","L07","L08","L10","L11","L12","L13","L14","L20","L21","L22","L23","L24","L25","L26","L27","L28","L29","L30","L40","L41","L42",
		"L43","L44","L45","L50","L51","L52","L53","L54","L55","L56","L57","L58","L59","L60","L61","L62","L63","L64","L65","L66","L67","L68","L69","L70","L71","L72","L73","L74",
		"L75","L80","L81","L82","L83","L84","L85","L86","L87","L88","L89","L90","L91","L92","L93","L94","L95","L96","L97","L98","L99"
	] 
},
{
	"name": "Diseases of the musculoskeletal system and connective tissue", 
	"values": 
	[
		"M00","M01","M02","M03","M04","M05","M06","M07","M08","M09","M10","M11","M12","M13","M14","M15","M16","M17","M18","M19","M20","M21","M22","M23","M24","M25",
		"M30","M31","M32","M33","M34","M35","M36","M40","M41","M42","M43","M44","M45","M46","M47","M48","M49","M50","M51","M52","M53","M54","M60","M61","M62","M63",
		"M64","M65","M66","M67","M68","M69","M70","M71","M72","M73","M74","M75","M76","M77","M78","M79","M80","M81","M82","M83","M84","M85","M86","M87","M88","M89",
		"M90","M91","M92","M93","M94","M95","M96","M97","M98","M99"
	] 
},
{
	"name": "Diseases of the genitourinary system", 
	"values": 
	[
		"N00","N01","N02","N03","N04","N05","N06","N07","N08","N10","N11","N12","N13","N14","N15","N16","N17","N18","N19","N20","N21","N22","N23","N25","N26","N27","N28",
		"N29","N30","N31","N32","N33","N34","N35","N36","N37","N38","N39","N40","N41","N42","N43","N44","N45","N46","N47","N48","N49","N50","N51","N60","N61","N62","N63",
		"N64","N70","N71","N72","N73","N74","N75","N76","N77","N80","N81","N82","N83","N84","N85","N86","N87","N88","N89","N90","N91","N92","N93","N94","N95","N96","N97",
		"N98","N99"
	] 
},
{
	"name": "Pregnancy, childbirth and the puerperium", 
	"values": 
	[
		"O00","O01","O02","O03","O04","O05","O06","O07","O08","O10","O11","O12","O13","O14","O15","O16","O20","O21","O22","O23","O24","O25","O26","O27","O28","O29",
		"O30","O31","O32","O33","O34","O35","O36","O37","O38","O39","O40","O41","O42","O43","O44","O45","O46","O47","O48","O60","O61","O62","O63","O64","O65","O66",
		"O67","O68","O69","O70","O71","O72","O73","O74","O75","O80","O81","O82","O83","O84","O85","O86","O87","O88","O89","O90","O91","O92","O94","O95","O96","O97",
		"O98","O99"
	] 
},
{
	"name": "Certain conditions originating in the perinatal period", 
	"values": 
	[
		"P00","P01","P02","P03","P04","P05","P06","P07","P08","P10","P11","P12","P13","P14","P15","P20","P21","P22","P23","P24","P25","P26","P27","P28","P29","P35","P36","P37",
		"P38","P39","P50","P51","P52","P53","P54","P55","P56","P57","P58","P59","P60","P61","P70","P71","P72","P73","P74","P75","P76","P77","P78","P80","P81","P82","P83","P90",
		"P91","P92","P93","P94","P95","P96"
	] 
},
{
	"name": "Congenital malformations, deformations and chromosomal abnormalities", 
	"values": 
	[
		"Q00","Q01","Q02","Q03","Q04","Q05","Q06","Q07","Q10","Q11","Q12","Q13","Q14","Q15","Q16","Q17","Q18","Q20","Q21","Q22","Q23","Q24","Q25","Q26","Q27","Q28","Q30",
		"Q31","Q32","Q33","Q34","Q35","Q36","Q37","Q38","Q39","Q40","Q41","Q42","Q43","Q44","Q45","Q50","Q51","Q52","Q53","Q54","Q55","Q56","Q60","Q61","Q62","Q63","Q64",
		"Q65","Q66","Q67","Q68","Q69","Q70","Q71","Q72","Q73","Q74","Q75","Q76","Q77","Q78","Q79","Q80","Q81","Q82","Q83","Q84","Q85","Q86","Q87","Q88","Q89","Q90","Q91",
		"Q92","Q93","Q94","Q95","Q96","Q97","Q98","Q99"
	] 
},
{
	"name": "Symptoms, signs and abnormal clinical and laboratory findings, not elsewhere classified", 
	"values": 
	[
		"R00","R01","R02","R03","R04","R05","R06","R07","R08","R09","R10","R11","R12","R13","R14","R15","R16","R17","R18","R19","R20","R21","R22","R23","R25","R26","R27","R28",
		"R29","R30","R31","R32","R33","R34","R35","R36","R37","R38","R39","R40","R41","R42","R43","R44","R45","R46","R47","R48","R49","R50","R51","R52","R53","R54","R55","R56",
		"R57","R58","R59","R60","R61","R62","R63","R64","R65","R66","R67","R68","R69","R70","R71","R72","R73","R74","R75","R76","R77","R78","R79","R80","R81","R82","R83","R84",
		"R85","R86","R87","R88","R89","R90","R91","R92","R93","R94","R95","R96","R97","R98","R99"
	] 
},
{
	"name": "Injury, poisoning and certain other consequences of external causes", 
	"values": 
	[
		"S00","S01","S02","S03","S04","S05","S06","S07","S08","S09","S10","S11","S12","S13","S14","S15","S16","S17","S18","S19","S20","S21","S22","S23","S24","S25","S26","S27","S28",
		"S29","S30","S31","S32","S33","S34","S35","S36","S37","S38","S39","S40","S41","S42","S43","S44","S45","S46","S47","S48","S49","S50","S51","S52","S53","S54","S55","S56","S57",
		"S58","S59","S60","S61","S62","S63","S64","S65","S66","S67","S68","S69","S70","S71","S72","S73","S74","S75","S76","S77","S78","S79","S80","S81","S82","S83","S84","S85","S86",
		"S87","S88","S89","S90","S91","S92","S93","S94","S95","S96","S97","S98","S99",
		"T00","T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","T11","T12","T13","T14","T15","T16","T17","T18","T19","T20","T21","T22","T23","T24","T25","T26","T27","T28","T29",
		"T30","T31","T32","T33","T34","T35","T36","T37","T38","T39","T40","T41","T42","T43","T44","T45","T46","T47","T48","T49","T50","T51","T52","T53","T54","T55","T56","T57","T58","T59",
		"T60","T61","T62","T63","T64","T65","T66","T67","T68","T69","T70","T71","T72","T73","T74","T75","T76","T77","T78","T79","T80","T81","T82","T83","T84","T85","T86","T87","T88","T90",
		"T91","T92","T93","T94","T95","T96","T97","T98"
	] 
},
{
	"name": "External causes of morbidity and mortality", 
	"values": 
	[
		"V00","V01","V02","V03","V04","V05","V06","V07","V08","V09","V10","V11","V12","V13","V14","V15","V16","V17","V18","V19","V20","V21","V22","V23","V24","V25","V26","V27","V28","V29",
		"V30","V31","V32","V33","V34","V35","V36","V37","V38","V39","V40","V41","V42","V43","V44","V45","V46","V47","V48","V49","V50","V51","V52","V53","V54","V55","V56","V57","V58","V59",
		"V60","V61","V62","V63","V64","V65","V66","V67","V68","V69","V70","V71","V72","V73","V74","V75","V76","V77","V78","V79","V80","V81","V82","V83","V84","V85","V86","V87","V88","V89",
		"V90","V91","V92","V93","V94","V95","V96","V97","V98","V99",
		"X00","X01","X02","X03","X04","X05","X06","X07","X08","X09","X10","X11","X12","X13","X14","X15","X16","X17","X18","X19","X20","X21","X22","X23","X24","X25","X26","X27","X28","X29",
		"X30","X31","X32","X33","X34","X35","X36","X37","X38","X39","X40","X41","X42","X43","X44","X45","X46","X47","X48","X49","X50","X51","X52","X53","X54","X55","X56","X57","X58","X59",
		"X60","X61","X62","X63","X64","X65","X66","X67","X68","X69","X70","X71","X72","X73","X74","X75","X76","X77","X78","X79","X80","X81","X82","X83","X84","X85","X86","X87","X88","X89",
		"X90","X91","X92","X93","X94","X95","X96","X97","X98","X99",
		"Y00","Y01","Y02","Y03","Y04","Y05","Y06","Y07","Y08","Y09","Y10","Y11","Y12","Y13","Y14","Y15","Y16","Y17","Y18","Y19","Y20","Y21","Y22","Y23","Y24","Y25","Y26","Y27","Y28","Y29",
		"Y30","Y31","Y32","Y33","Y34","Y35","Y36","Y37","Y38","Y39","Y40","Y41","Y42","Y43","Y44","Y45","Y46","Y47","Y48","Y49","Y50","Y51","Y52","Y53","Y54","Y55","Y56","Y57","Y58","Y59",
		"Y60","Y61","Y62","Y63","Y64","Y65","Y66","Y67","Y68","Y69","Y70","Y71","Y72","Y73","Y74","Y75","Y76","Y77","Y78","Y79","Y80","Y81","Y82","Y83","Y84","Y85","Y86","Y87","Y88","Y89",
		"Y90","Y91","Y92","Y93","Y94","Y95","Y96","Y97","Y98","Y99"
	] 
},
{
	"name": "Factors influencing health status and contact with health services", 
	"values": 
	[
		"Z00","Z01","Z02","Z03","Z04","Z05","Z06","Z07","Z08","Z09","Z10","Z11","Z12","Z13","Z20","Z21","Z22","Z23","Z24","Z25","Z26","Z27","Z28","Z29","Z30","Z31","Z32","Z33","Z34","Z35","Z36",
		"Z37","Z38","Z39","Z40","Z41","Z42","Z43","Z44","Z45","Z46","Z47","Z48","Z49","Z50","Z51","Z52","Z53","Z54","Z55","Z56","Z57","Z58","Z59","Z60","Z61","Z62","Z63","Z64","Z65","Z70","Z71",
		"Z72","Z73","Z74","Z75","Z76","Z80","Z81","Z82","Z83","Z84","Z85","Z86","Z87","Z88","Z89","Z90","Z91","Z92","Z93","Z94","Z95","Z96","Z97","Z98","Z99"
	] 
},
{
	"name": "Special purposes ", 
	"values": 
	[
		"U00","U01","U02","U03","U04","U05","U06","U07","U08","U09","U10","U11","U12","U13","U14","U15","U16","U17","U18","U19","U20","U21","U22","U23","U24","U25","U26","U27","U28","U29",
		"U30","U31","U32","U33","U34","U35","U36","U37","U38","U39","U40","U41","U42","U43","U44","U45","U46","U47","U48","U49","U82","U83","U84","U85"
	] 
}
]);

db.createCollection("icd10_level2_dictionaries");
db.icd10_level2_dictionaries.deleteMany({});
db.icd10_level2_dictionaries.insertMany([
    {
        "name":"Intestinal infectious diseases",
        "values":
        [
            "A00","A01","A02","A03","A04","A05","A06","A07","A08","A09"
        ]

    },
    {
        "name":"Tuberculosis",
        "values":
        [
            "A15","A16","A17","A18","A19"
        ]
    },
    {
        "name":"Certain zoonotic bacterial diseases",
        "values":
        [
            "A20","A21","A22","A23","A24","A25","A26","A27","A28"
        ]
    },
    {
        "name":"Other bacterial diseases",
        "values":
        [
            "A30","A31","A32","A33","A34","A35","A36","A37","A38","A39","A40","A41","A42","A43","A44","A45","A46","A47","A48","A49"
        ]
    },
    {
        "name":"Infections with a predominantly sexual mode of transmission",
        "values":
        [
            "A50","A51","A52","A53","A54","A55","A56","A57","A58","A59","A60","A61","A62","A63","A64"
        ]
    },
    {
        "name":"Other spirochaetal diseases",
        "values":
        [
            "A65","A66","A67","A68","A69"
        ]
    },
    {
        "name":"Other diseases caused by chlamydiae",
        "values":
        [
            "A70","A71","A74"
        ]
    },
    {
        "name":"Rickettsioses",
        "values":
        [
            "A75","A77","A78","A79"
        ]
    },
    {
        "name":"Viral infections of the central nervous system",
        "values":
        [
            "A80","A81","A82","A83","A84","A85","A86","A87","A88","A89"
        ]
    },
    {
        "name":"Arthropod-borne viral fevers and viral haemorrhagic fevers",
        "values":
        [
            "A92","A93","A94","A95","A96","A97","A98","A99"
        ]
    },
    {
        "name":"Viral infections characterized by skin and mucous membrane lesions",
        "values":
        [
            "B00","B01","B02","B03","B04","B05","B06","B07","B08","B09"
        ]
    },
    {
        "name":"Viral hepatitis",
        "values":
        [
            "B15","B16","B17","B18","B19"
        ]
    },
    {
        "name":"B20-B24 Human immunodeficiency virus [HIV] disease",
        "values":
        [
            "B20","B21","B22","B23","B24"
        ]
    },
    {
        "name":"Other viral diseases",
        "values":
        [
            "B25","B26","B27","B30","B33","B34"
        ]
    },
    {
        "name":"Mycoses",
        "values":
        [
            "B35","B36","B37","B38","B39","B40","B41","B42","B43","B44","B45","B46","B47","B48","B49"
        ]
    },
    {
        "name":"Protozoal diseases",
        "values":
        [
            "B50","B51","B52","B53","B54","B55","B56","B57","B58","B59","B60","B64"
        ]
    },
    {
        "name":"Helminthiases",
        "values":
        [
            "B65","B66","B67","B68","B69","B70","B71","B72","B73","B74","B75","B76","B77","B78","B79","B80","B81","B82","B83"
        ]
    },
    {
        "name":"Pediculosis, acariasis and other infestations",
        "values":
        [
            "B85","B86","B87","B88","B89"
        ]
    },
    {
        "name":"Sequelae of infectious and parasitic diseases",
        "values":
        [
            "B90","B91","B92","B94"
        ]
    },
    {
        "name":"Bacterial, viral and other infectious agents",
        "values":
        [
            "B95","B96","B97","B98"
        ]
    },
    {
        "name":"Other infectious diseases",
        "values":
        [
            "B99"
        ]
    },
    {
        "name":"Malignant neoplasms",
        "values":
        [
            "C00","C01","C02","C03","C04","C05","C06","C07","C08","C09","C10","C11","C12","C13","C14","C15","C16","C17","C18","C19","C20","C21","C22","C23","C24","C25",
		    "C26","C27","C28","C29","C30","C31","C32","C33","C34","C35","C36","C37","C38","C39","C40","C41","C42","C43","C44","C45","C46","C47","C48","C49","C50","C51",
		    "C52","C53","C54","C55","C56","C57","C58","C59","C60","C61","C62","C63","C64","C65","C66","C67","C68","C69","C70","C71","C72","C73","C74","C75","C76","C77",
		    "C78","C79","C80","C81","C82","C83","C84","C85","C86","C87","C88","C89","C90","C91","C92","C93","C94","C95","C96","C97"
        ]
    },
    {
        "name":"In situ neoplasms",
        "values":
        [
            "D00","D01","D02","D03","D04","D05","D06","D07","D08","D09"
        ]
    },
    {
        "name":"Benign neoplasms",
        "values":
        [
            "D10","D11","D12","D13","D14","D15","D16","D17","D18","D19","D20","D21","D22","D23","D24","D25",
		    "D26","D27","D28","D29","D30","D31","D32","D33","D34","D35","D36"
        ]
    },
    {
        "name":"Neoplasms of uncertain or unknown behaviour",
        "values":
        [
            "D37","D38","D39","D40","D41","D42","D43","D44","D45","D46","D47","D48"
        ]
    },
    {
        "name":"Nutritional anaemias",
        "values":
        [
            "D50","D51","D52","D53"
        ]
    },
    {
        "name":"Haemolytic anaemias",
        "values":
        [
            "D55","D56","D57","D58","D59"
        ]
    },
    {
        "name":"Aplastic and other anaemias",
        "values":
        [
            "D60","D61","D62","D63","D64"
        ]
    },
    {
        "name":"Coagulation defects, purpura and other haemorrhagic conditions",
        "values":
        [
            "D65","D66","D67","D68","D69"
        ]
    },
    {
        "name":"Other diseases of blood and blood-forming organs",
        "values":
        [
            "D70","D71","D72","D73","D74","D75","D76","D77"
        ]
    },
    {
        "name":"Certain disorders involving the immune mechanism",
        "values":
        [
            "D80","D81","D82","D83","D84","D85","D86","D87","D88","D89"
        ]
    },
    {
        "name":"Disorders of thyroid gland",
        "values":
        [
            "E00","E01","E02","E03","E04","E05","E06","E07"
        ]
    },
    {
        "name":"Diabetes mellitus",
        "values":
        [
            "E10","E11","E12","E13","E14"
        ]
    },
    {
        "name":"Other disorders of glucose regulation and pancreatic internal secretion",
        "values":
        [
            "E15","E16"
        ]
    },
    {
        "name":"Disorders of other endocrine glands",
        "values":
        [
            "E20","E21","E22","E23","E24","E25","E26","E27","E28","E29","E30","E31","E32","E33","E34","E35"
        ]
    },
    {
        "name":"Malnutrition",
        "values":
        [
            "E40","E41","E42","E43","E44","E45","E46"
        ]
    },
    {
        "name":"Other nutritional deficiencies",
        "values":
        [
            "E50","E51","E52","E53","E54","E55","E56","E57","E58","E59","E60","E61","E62","E63","E64"
        ]
    },
    {
        "name":"Obesity and other hyperalimentation",
        "values":
        [
            "E65","E66","E67","E68"
        ]
    },
    {
        "name":"Metabolic disorders",
        "values":
        [
            "E70","E71","E72","E73","E74","E75","E76","E77","E78","E79","E80","E81","E82","E83","E84","E85","E86","E87","E88","E89","E90"
        ]
    },
    {
        "name":"Organic, including symptomatic, mental disorders",
        "values":
        [
            "F00","F01","F02","F03","F04","F05","F06","F07","F08","F09"
        ]
    },
    {
        "name":"Mental and behavioural disorders due to psychoactive substance use",
        "values":
        [
            "F10","F11","F12","F13","F14","F15","F16","F17","F18","F19"
        ]
    },
    {
        "name":"Schizophrenia, schizotypal and delusional disorders",
        "values":
        [
            "F20","F21","F22","F23","F24","F25","F26","F27","F28","F29"
        ]
    },
    {
        "name":"Mood [affective] disorders",
        "values":
        [
            "F30","F31","F32","F33","F34","F35","F36","F37","F38","F39"
        ]
    },
    {
        "name":"Neurotic, stress-related and somatoform disorders",
        "values":
        [
            "F40","F41","F42","F43","F44","F45","F48"
        ]
    },
    {
        "name":"Behavioural syndromes associated with physiological disturbances and physical factors",
        "values":
        [
            "F50","F51","F52","F53","F54","F55","F59"
        ]
    },
    {
        "name":"Disorders of adult personality and behaviour",
        "values":
        [
            "F60","F61","F62","F63","F64","F65","F66","F68","F69"
        ]
    },
    {
        "name":"Mental retardation",
        "values":
        [
            "F70","F71","F72","F73","F78","F79"
        ]
    },
    {
        "name":"Disorders of psychological development",
        "values":
        [
            "F80","F81","F82","F83","F84","F88","F89"
        ]
    },
    {
        "name":"Behavioural and emotional disorders with onset usually occurring in childhood and adolescence",
        "values":
        [
            "F90","F91","F92","F93","F94","F95","F98"
        ]
    },
    {
        "name":"Unspecified mental disorder",
        "values":
        [
            "F99"
        ]
    },
    {
        "name":"Inflammatory diseases of the central nervous system",
        "values":
        [
            "G00","G01","G02","G03","G04","G05","G06","G07","G08","G09"
        ]
    },
    {
        "name":"Systemic atrophies primarily affecting the central nervous system",
        "values":
        [
            "G10","G11","G12","G13","G14"
        ]
    },
    {
        "name":"Extrapyramidal and movement disorders",
        "values":
        [
            "G20","G21","G22","G23","G24","G25","G26"
        ]
    },
    {
        "name":"Other degenerative diseases of the nervous system",
        "values":
        [
            "G30","G31","G32"
        ]
    },
    {
        "name":"Demyelinating diseases of the central nervous system",
        "values":
        [
            "G35","G36","G37"
        ]
    },
    {
        "name":"Episodic and paroxysmal disorders",
        "values":
        [
            "G40","G41","G42","G43","G44","G45","G46","G47"
        ]
    },
    {
        "name":"Nerve, nerve root and plexus disorders",
        "values":
        [
            "G50","G51","G52","G53","G54","G55","G56","G57","G58","G59"
        ]
    },
    {
        "name":"Polyneuropathies and other disorders of the peripheral nervous system",
        "values":
        [
            "G60","G61","G62","G63","G64"
        ]
    },
    {
        "name":"Diseases of myoneural junction and muscle",
        "values":
        [
            "G70","G71","G72","G73"
        ]
    },
    {
        "name":"Cerebral palsy and other paralytic syndromes",
        "values":
        [
            "G80","G81","G82","G83"
        ]
    },
    {
        "name":"Other disorders of the nervous system",
        "values":
        [
            "G90","G91","G92","G93","G94","G95","G96","G97","G98","G99"
        ]
    },
    {
        "name":"Disorders of eyelid, lacrimal system and orbit",
        "values":
        [
            "H00","H01","H02","H03","H04","H05","H06"
        ]
    },
    {
        "name":"Disorders of conjunctiva",
        "values":
        [
            "H10","H11","H12","H13"
        ]
    },
    {
        "name":"Disorders of sclera, cornea, iris and ciliary body",
        "values":
        [
            "H15","H16","H17","H18","H19","H20","H21","H22"
        ]
    },
    {
        "name":"Disorders of lens",
        "values":
        [
            "H25","H26","H27","H28"
        ]
    },
    {
        "name":"Disorders of choroid and retina",
        "values":
        [
            "H30","H31","H32","H33","H34","H35","H36"
        ]
    },
    {
        "name":"Glaucoma",
        "values":
        [
            "H40","H42"
        ]
    },
    {
        "name":"Disorders of vitreous body and globe",
        "values":
        [
            "H43","H44","H45"
        ]
    },
    {
        "name":"Disorders of optic nerve and visual pathways",
        "values":
        [
            "H46","H47","H48"
        ]
    },
    {
        "name":"Disorders of ocular muscles, binocular movement, accommodation and refraction",
        "values":
        [
            "H49","H50","H51","H52"
        ]
    },
    {
        "name":"Visual disturbances and blindness",
        "values":
        [
            "H53","H54"
        ]
    },
    {
        "name":"Other disorders of eye and adnexa",
        "values":
        [
            "H55","H56","H57","H58","H59"
        ]
    },
    {
        "name":"Diseases of external ear",
        "values":
        [
            "H60","H61","H62"
        ]
    },
    {
        "name":"Diseases of middle ear and mastoid",
        "values":
        [
            "H65","H66","H67","H68","H69","H70","H71","H72","H73","H74","H75"
        ]
    },
    {
        "name":"Diseases of inner ear",
        "values":
        [
            "H80","H81","H82","H83"
        ]
    },
    {
        "name":"Other disorders of ear",
        "values":
        [
            "H90","H91","H92","H93","H94","H95"
        ]
    },
    {
        "name":"Acute rheumatic fever",
        "values":
        [
            "I00","I01","I02"
        ]
    },
    {
        "name":"Chronic rheumatic heart diseases",
        "values":
        [
            "I05","I06","I07","I08","I09"
        ]
    },
    {
        "name":"Hypertensive diseases",
        "values":
        [
            "I10","I11","I12","I13","I14","I15"
        ]
    },
    {
        "name":"Ischaemic heart diseases",
        "values":
        [
            "I20","I21","I22","I23","I24","I25"
        ]
    },
    {
        "name":"Pulmonary heart disease and diseases of pulmonary circulation",
        "values":
        [
            "I26","I27","I28"
        ]
    },
    {
        "name":"Other forms of heart disease",
        "values":
        [
            "I30","I31","I32","I33","I34","I35","I36","I37","I38","I39","I40","I41","I42","I43","I44","I45","I46","I47","I48","I49","I50","I51","I52"
        ]
    },
    {
        "name":"Cerebrovascular diseases",
        "values":
        [
            "I60","I61","I62","I63","I64","I65","I66","I67","I68","I69"
        ]
    },
    {
        "name":"Diseases of arteries, arterioles and capillaries",
        "values":
        [
            "I70","I71","I72","I73","I74","I75","I76","I77","I78","I79"
        ]
    },
    {
        "name":"Diseases of veins, lymphatic vessels and lymph nodes, not elsewhere classified",
        "values":
        [
            "I80","I81","I82","I83","I84","I85","I86","I87","I88","I89"
        ]
    },
    {
        "name":"Other and unspecified disorders of the circulatory system",
        "values":
        [
            "I95","I96","I97","I98","I99"
        ]
    },
    {
        "name":"Acute upper respiratory infections",
        "values":
        [
            "J00","J01","J02","J03","J04","J05","J06"
        ]
    },
    {
        "name":"Influenza and pneumonia",
        "values":
        [
            "J09","J10","J11","J12","J13","J14","J15","J16","J17","J18"
        ]
    },
    {
        "name":"Other acute lower respiratory infections",
        "values":
        [
            "J20","J21","J22"
        ]
    },
    {
        "name":"Other diseases of upper respiratory tract",
        "values":
        [
            "J30","J31","J32","J33","J34","J35","J36","J37","J38","J39"
        ]
    },
    {
        "name":"Chronic lower respiratory diseases",
        "values":
        [
            "J40","J41","J42","J43","J44","J45","J46","J47"
        ]
    },
    {
        "name":"Lung diseases due to external agents",
        "values":
        [
            "J60","J61","J62","J63","J64","J65","J66","J67","J68","J69","J70"
        ]
    },
    {
        "name":"Other respiratory diseases principally affecting the interstitium",
        "values":
        [
            "J80","J81","J82","J83","J84"
        ]
    },
    {
        "name":"Suppurative and necrotic conditions of lower respiratory tract",
        "values":
        [
            "J85","J86"
        ]
    },
    {
        "name":"Other diseases of pleura",
        "values":
        [
            "J90","J91","J92","J93","J94"
        ]
    },
    {
        "name":"Other diseases of the respiratory system",
        "values":
        [
            "J95","J96","J97","J98","J99"
        ]
    },
    {
        "name":"Diseases of oral cavity, salivary glands and jaws",
        "values":
        [
            "K00","K01","K02","K03","K04","K05","K06","K07","K08","K09","K10","K11","K12","K13","K14"
        ]
    },
    {
        "name":"Diseases of oesophagus, stomach and duodenum",
        "values":
        [
            "K20","K21","K22","K23","K25","K26","K27","K28","K29","K30","K31"
        ]
    },
    {
        "name":"Diseases of appendix",
        "values":
        [
            "K35","K36","K37","K38"
        ]
    },
    {
        "name":"Hernia",
        "values":
        [
            "K40","K41","K42","K43","K44","K45","K46"
        ]
    },
    {
        "name":"Noninfective enteritis and colitis",
        "values":
        [
            "K50","K51","K52"
        ]
    },
    {
        "name":"Other diseases of intestines",
        "values":
        [
            "K55","K56","K57","K58","K59","K60","K61","K62","K63","K64"
        ]
    },
    {
        "name":"Diseases of peritoneum",
        "values":
        [
            "K65","K66","K67"
        ]
    },
    {
        "name":"Diseases of liver",
        "values":
        [
            "K70","K71","K72","K73","K74","K75","K76","K77"
        ]
    },
    {
        "name":"Disorders of gallbladder, biliary tract and pancreas",
        "values":
        [
            "K80","K81","K82","K83","K85","K86","K87"
        ]
    },
    {
        "name":"Other diseases of the digestive system",
        "values":
        [
            "K90","K91","K92","K93"
        ]
    },
    {
        "name":"Infections of the skin and subcutaneous tissue",
        "values":
        [
            "L00","L01","L02","L03","L04","L05","L06","L07","L08"
        ]
    },
    {
        "name":"Bullous disorders",
        "values":
        [
            "L10","L11","L12","L13","L14"
        ]
    },
    {
        "name":"Dermatitis and eczema",
        "values":
        [
            "L20","L21","L22","L23","L24","L25","L26","L27","L28","L29","L30"
        ]
    },
    {
        "name":"Papulosquamous disorders",
        "values":
        [
            "L40","L41","L42","L43","L44","L45"
        ]
    },
    {
        "name":"Urticaria and erythema",
        "values":
        [
            "L50","L51","L52","L53","L54"
        ]
    },
    {
        "name":"Radiation-related disorders of the skin and subcutaneous tissue ",
        "values":
        [
            "L55","L56","L57","L58","L59"
        ]
    },
    {
        "name":"Disorders of skin appendages",
        "values":
        [
            "L60","L61","L62","L63","L64","L65","L66","L67","L68","L69","L70","L71","L72","L73","L74","L75"
        ]
    },
    {
        "name":"Other disorders of the skin and subcutaneous tissue",
        "values":
        [
            "L80","L81","L82","L83","L84","L85","L86","L87","L88","L89","L90","L91","L92","L93","L94","L95","L96","L97","L98","L99"
        ]
    },
    {
        "name":"Arthropathies",
        "values":
        [
            "M00","M01","M02","M03","M04","M05","M06","M07","M08","M09","M10","M11","M12","M13","M14","M15","M16","M17","M18","M19","M20","M21","M22","M23","M24","M25"
        ]
    },
    {
        "name":"Systemic connective tissue disorders",
        "values":
        [
            "M30","M31","M32","M33","M34","M35","M36"
        ]
    },
    {
        "name":"Dorsopathies",
        "values":
        [
            "M40","M41","M42","M43","M44","M45","M46","M47","M48","M49","M50","M51","M52","M53","M54"
        ]
    },
    {
        "name":"Soft tissue disorders",
        "values":
        [
            "M60","M61","M62","M63","M64","M65","M66","M67","M68","M69","M70","M71","M72","M73","M74","M75","M76","M77","M78","M79"
        ]
    },
    {
        "name":"Osteopathies and chondropathies",
        "values":
        [
            "M80","M81","M82","M83","M84","M85","M86","M87","M88","M89","M90","M91","M92","M93","M94"
        ]
    },
    {
        "name":"Other disorders of the musculoskeletal system and connective tissue",
        "values":
        [
            "M95","M96","M97","M98","M99"
        ]
    },
    {
        "name":"Glomerular diseases",
        "values":
        [
            "N00","N01","N02","N03","N04","N05","N06","N07","N08"
        ]
    },
    {
        "name":"Renal tubulo-interstitial diseases",
        "values":
        [
            "N10","N11","N12","N13","N14","N15","N16"
        ]
    },
    {
        "name":"Renal failure",
        "values":
        [
            "N17","N18","N19"
        ]
    },
    {
        "name":"Urolithiasis",
        "values":
        [
            "N20","N21","N22","N23"
        ]
    },
    {
        "name":"Other disorders of kidney and ureter",
        "values":
        [
            "N25","N26","N27","N28","N29"
        ]
    },
    {
        "name":"Other diseases of urinary system ",
        "values":
        [
            "N30","N31","N32","N33","N34","N35","N36","N37","N38","N39"
        ]
    },
    {
        "name":"Diseases of male genital organs",
        "values":
        [
            "N40","N41","N42","N43","N44","N45","N46","N47","N48","N49","N50","N51"
        ]
    },
    {
        "name":"Disorders of breast ",
        "values":
        [
            "N60","N61","N62","N63","N64"
        ]
    },
    {
        "name":"Inflammatory diseases of female pelvic organs",
        "values":
        [
            "N70","N71","N72","N73","N74","N75","N76","N77"
        ]
    },
    {
        "name":"Noninflammatory disorders of female genital tract",
        "values":
        [
            "N80","N81","N82","N83","N84","N85","N86","N87","N88","N89","N90","N91","N92","N93","N94","N95","N96","N97","N98"
        ]
    },
    {
        "name":"Other disorders of the genitourinary system",
        "values":
        [
            "N99"
        ]
    },
    {
        "name":"Pregnancy with abortive outcome",
        "values":
        [
            "O00","O01","O02","O03","O04","O05","O06","O07","O08"
        ]
    },
    {
        "name":"Oedema, proteinuria and hypertensive disorders in pregnancy, childbirth and the puerperium",
        "values":
        [
            "O10","O11","O12","O13","O14","O15","O16"
        ]
    },
    {
        "name":"Other maternal disorders predominantly related to pregnancy",
        "values":
        [
            "O20","O21","O22","O23","O24","O25","O26","O27","O28","O29"
        ]
    },
    {
        "name":"Maternal care related to the fetus and amniotic cavity and possible delivery problems",
        "values":
        [
            "O30","O31","O32","O33","O34","O35","O36","O37","O38","O39","O40","O41","O42","O43","O44","O45","O46","O47","O48"
        ]
    },
    {
        "name":"Complications of labour and delivery",
        "values":
        [
            "O61","O62","O63","O64","O65","O66","O67","O68","O69","O70","O71","O72","O73","O74","O75"
        ]
    },
    {
        "name":"Delivery",
        "values":
        [
            "O80","O81","O82","O83","O84"
        ]
    },
    {
        "name":"Complications predominantly related to the puerperium",
        "values":
        [
            "O85","O86","O87","O88","O89","O90","O91","O92"
        ]
    },
    {
        "name":"Other obstetric conditions, not elsewhere classified ",
        "values":
        [
            "O94","O95","O96","O97","O98","O99"
        ]
    },
    {
        "name":"Fetus and newborn affected by maternal factors and by complications of pregnancy, labour and delivery",
        "values":
        [
            "P00","P01","P02","P03","P04"
        ]
    },
    {
        "name":"Disorders related to length of gestation and fetal growth",
        "values":
        [
            "P05","P06","P07","P08"
        ]
    },
    {
        "name":"Birth trauma",
        "values":
        [
            "P10","P11","P12","P13","P14","P15"
        ]
    },
    {
        "name":"Respiratory and cardiovascular disorders specific to the perinatal period",
        "values":
        [
            "P20","P21","P22","P23","P24","P25","P26","P27","P28","P29"
        ]
    },
    {
        "name":"Infections specific to the perinatal period",
        "values":
        [
            "P35","P36","P37","P38","P39"
        ]
    },
    {
        "name":"Haemorrhagic and haematological disorders of fetus and newborn",
        "values":
        [
            "P50","P51","P52","P53","P54","P55","P56","P57","P58","P59","P60","P61"
        ]
    },
    {
        "name":"Transitory endocrine and metabolic disorders specific to fetus and newborn",
        "values":
        [
            "P70","P71","P72","P73","P74"
        ]
    },
    {
        "name":"Digestive system disorders of fetus and newborn",
        "values":
        [
            "P75","P76","P77","P78"
        ]
    },
    {
        "name":"Conditions involving the integument and temperature regulation of fetus and newborn",
        "values":
        [
            "P80","P81","P82","P83"
        ]
    },
    {
        "name":"Other disorders originating in the perinatal period",
        "values":
        [
            "P90","P91","P92","P93","P94","P95","P96"
        ]
    },
    {
        "name":"Congenital malformations of the nervous system",
        "values":
        [
            "Q00","Q01","Q02","Q03","Q04","Q05","Q06","Q07"
        ]
    },
    {
        "name":"Congenital malformations of eye, ear, face and neck",
        "values":
        [
            "Q10","Q11","Q12","Q13","Q14","Q15","Q16","Q17","Q18"
        ]
    },
    {
        "name":"Congenital malformations of the circulatory system",
        "values":
        [
            "Q20","Q21","Q22","Q23","Q24","Q25","Q26","Q27","Q28"
        ]
    },
    {
        "name":"Congenital malformations of the respiratory system",
        "values":
        [
            "Q30","Q31","Q32","Q33","Q34"
        ]
    },
    {
        "name":"Cleft lip and cleft palate",
        "values":
        [
            "Q35","Q36","Q37"
        ]
    },
    {
        "name":"Other congenital malformations of the digestive system",
        "values":
        [
            "Q38","Q39","Q40","Q41","Q42","Q43","Q44","Q45"
        ]
    },
    {
        "name":"Congenital malformations of genital organs",
        "values":
        [
            "Q50","Q51","Q52","Q53","Q54","Q55","Q56"
        ]
    },
    {
        "name":"Congenital malformations of the urinary system",
        "values":
        [
            "Q60","Q61","Q62","Q63","Q64"
        ]
    },
    {
        "name":"Congenital malformations and deformations of the musculoskeletal system",
        "values":
        [
            "Q65","Q66","Q67","Q68","Q69","Q70","Q71","Q72","Q73","Q74","Q75","Q76","Q77","Q78","Q79"
        ]
    },
    {
        "name":"Other congenital malformations",
        "values":
        [
            "Q80","Q81","Q82","Q83","Q84","Q85","Q86","Q87","Q88","Q89"
        ]
    },
    {
        "name":"Chromosomal abnormalities, not elsewhere classified",
        "values":
        [
            "Q90","Q91","Q92","Q93","Q94","Q95","Q96","Q97","Q98","Q99"
        ]
    },
    {
        "name":"Symptoms and signs involving the circulatory and respiratory systems",
        "values":
        [
            "R00","R01","R02","R03","R04","R05","R06","R07","R08","R09"
        ]
    },
    {
        "name":"Symptoms and signs involving the digestive system and abdomen",
        "values":
        [
            "R10","R11","R12","R13","R14","R15","R16","R17","R18","R19"
        ]
    },
    {
        "name":"Symptoms and signs involving the skin and subcutaneous tissue",
        "values":
        [
            "R20","R21","R22","R23"
        ]
    },
    {
        "name":"Symptoms and signs involving the nervous and musculoskeletal systems",
        "values":
        [
            "R25","R26","R27","R28","R29"
        ]
    },
    {
        "name":"Symptoms and signs involving the urinary system",
        "values":
        [
            "R30","R31","R32","R33","R34","R35","R36","R37","R38","R39"
        ]
    },
    {
        "name":"Symptoms and signs involving cognition, perception, emotional state and behaviour",
        "values":
        [
            "R40","R41","R42","R43","R44","R45","R46"
        ]
    },
    {
        "name":"Symptoms and signs involving speech and voice",
        "values":
        [
            "R47","R48","R49"
        ]
    },
    {
        "name":"General symptoms and signs",
        "values":
        [
            "R50","R51","R52","R53","R54","R55","R56","R57","R58","R59","R60","R61","R62","R63","R64","R65","R66","R67","R68","R69"
        ]
    },
    {
        "name":"Abnormal findings on examination of blood, without diagnosis",
        "values":
        [
            "R70","R71","R72","R73","R74","R75","R76","R77","R78","R79"
        ]
    },
    {
        "name":"Abnormal findings on examination of urine, without diagnosis",
        "values":
        [
            "R80","R81","R82"
        ]
    },
    {
        "name":"Abnormal findings on examination of other body fluids, substances and tissues, without diagnosis",
        "values":
        [
            "R83","R84","R85","R86","R87","R88","R89"
        ]
    },
    {
        "name":"Abnormal findings on diagnostic imaging and in function studies, without diagnosis",
        "values":
        [
            "R90","R91","R92","R93","R94"
        ]
    },
    {
        "name":"Ill-defined and unknown causes of mortality",
        "values":
        [
            "R95","R96","R97","R98","R99"
        ]
    },
    {
        "name":"Injuries to the head",
        "values":
        [
            "S00","S01","S02","S03","S04","S05","S06","S07","S08","S09"
        ]
    },
    {
        "name":"Injuries to the neck ",
        "values":
        [
            "S10","S11","S12","S13","S14","S15","S16","S17","S18","S19"
        ]
    },
    {
        "name":"Injuries to the thorax",
        "values":
        [
            "S20","S21","S22","S23","S24","S25","S26","S27","S28","S29"
        ]
    },
    {
        "name":"Injuries to the abdomen, lower back, lumbar spine and pelvis",
        "values":
        [
            "S30","S31","S32","S33","S34","S35","S36","S37","S38","S39"
        ]
    },
    {
        "name":"Injuries to the shoulder and upper arm",
        "values":
        [
            "S40","S41","S42","S43","S44","S45","S46","S47","S48","S49"
        ]
    },
    {
        "name":"Injuries to the elbow and forearm",
        "values":
        [
            "S50","S51","S52","S53","S54","S55","S56","S57","S58","S59"
        ]
    },
    {
        "name":"Injuries to the wrist and hand",
        "values":
        [
            "S60","S61","S62","S63","S64","S65","S66","S67","S68","S69"
        ]
    },
    {
        "name":"Injuries to the hip and thigh",
        "values":
        [
            "S70","S71","S72","S73","S74","S75","S76","S77","S78","S79"
        ]
    },
    {
        "name":"Injuries to the knee and lower leg",
        "values":
        [
            "S80","S81","S82","S83","S84","S85","S86","S87","S88","S89"
        ]
    },
    {
        "name":"Injuries to the ankle and foot",
        "values":
        [
            "S90","S91","S92","S93","S94","S95","S96","S97","S98","S99"
        ]
    },
    {
        "name":"Injuries involving multiple body regions",
        "values":
        [
            "T00","T01","T02","T03","T04","T05","T06","T07"
        ]
    },
    {
        "name":"Injuries to unspecified part of trunk, limb or body region",
        "values":
        [
            "T08","T09","T10","T11","T12","T13","T14"
        ]
    },
    {
        "name":"Effects of foreign body entering through natural orifice",
        "values":
        [
            "T15","T16","T17","T18","T19"
        ]
    },
    {
        "name":"Burns and corrosions",
        "values":
        [
            "T20","T21","T22","T23","T24","T25","T26","T27","T28","T29","T30","T31","T32"
        ]
    },
    {
        "name":"Frostbite",
        "values":
        [
            "T33","T34","T35"
        ]
    },
    {
        "name":"Poisoning by drugs, medicaments and biological substances",
        "values":
        [
            "T36","T37","T38","T39","T40","T41","T42","T43","T44","T45","T46","T47","T48","T49","T50"
        ]
    },
    {
        "name":"Toxic effects of substances chiefly nonmedicinal as to source",
        "values":
        [
            "T51","T52","T53","T54","T55","T56","T57","T58","T59","T60","T61","T62","T63","T64","T65"
        ]
    },
    {
        "name":"Other and unspecified effects of external causes",
        "values":
        [
            "T66","T67","T68","T69","T70","T71","T72","T73","T74","T75","T76","T77","T78"
        ]
    },
    {
        "name":"Certain early complications of trauma",
        "values":
        [
            "T79"
        ]
    },
    {
        "name":"Complications of surgical and medical care, not elsewhere classified",
        "values":
        [
            "T80","T81","T82","T83","T84","T85","T86","T87","T88"
        ]
    },
    {
        "name":"Sequelae of injuries, of poisoning and of other consequences of external causes",
        "values":
        [
            "T90","T91","T92","T93","T94","T95","T96","T97","T98"
        ]
    },
    {
        "name":"Accidents",
        "values":
        [
            "V00","V01","V02","V03","V04","V05","V06","V09","V10","V11","V12","V13","V14","V15","V16","V17","V18","V19","V20","V21","V22","V23","V24","V25","V26","V27","V28","V29",
		    "V30","V31","V32","V33","V34","V35","V36","V37","V38","V39","V40","V41","V42","V43","V44","V45","V46","V47","V48","V49","V50","V51","V52","V53","V54","V55","V56","V57","V58","V59",
		    "V60","V61","V62","V63","V64","V65","V66","V67","V68","V69","V70","V71","V72","V73","V74","V75","V76","V77","V78","V79","V80","V81","V82","V83","V84","V85","V86","V87","V88","V89",
		    "V90","V91","V92","V93","V94","V95","V96","V97","V98","V99",
		    "W00","W01","W02","W03","W04","W05","W06","W07","W08","W09","W10","W11","W12","W13","W14","W15","W16","W17","W18","W19","W20","W21","W22","W23","W24","W25","W26","W26","W27","W28",
		    "W29","W30","W31","W32","W33","W34","W35","W36","W37","W38","W39","W40","W41","W42","W43","W44","W45","W46","W49","W50","W51","W52","W53","W54","W55","W56","W57","W58","W59","W60",
		    "W64","W65","W66","W67","W68","W69","W70","W73","W74","W75","W76","W77","W78","W79","W80","W81","W83","W84","W85","W86","W87","W88","W89","W90","W91","W92","W93","W94","W99",
		    "X00","X01","X02","X03","X04","X05","X06","X07","X08","X09","X10","X11","X12","X13","X14","X15","X16","X17","X18","X19","X20","X21","X22","X23","X24","X25","X26","X27","X28","X29",
		    "X30","X31","X32","X33","X34","X35","X36","X37","X38","X39","X40","X41","X42","X43","X44","X45","X46","X47","X48","X49","X50","X51","X52","X53","X54","X55","X56","X57","X58","X59"
        ]
    },
    {
        "name":"Intentional self-harm",
        "values":
        [
            "X60","X61","X62","X63","X64","X65","X66","X67","X68","X69","X70","X71","X72","X73","X74","X75","X76","X77","X78","X79","X80","X81","X82","X83","X84"
        ]
    },
    {
        "name":"Assault",
        "values":
        [
            "X85","X86","X87","X88","X89","X90","X91","X92","X93","X94","X95","X96","X97","X98","X99","Y00","Y01","Y02","Y03","Y04","Y05","Y06","Y07","Y08","Y09"
        ]
    },
    {
        "name":"Event of undetermined intent",
        "values":
        [
            "Y10","Y11","Y12","Y13","Y14","Y15","Y16","Y17","Y18","Y19","Y20","Y21","Y22","Y23","Y24","Y25","Y26","Y27","Y28","Y29",
            "Y30","Y31","Y32","Y33","Y34"
        ]
    },
    {
        "name":"Legal intervention and operations of war",
        "values":
        [
            "Y35","Y36"
        ]
    },
    {
        "name":"Complications of medical and surgical care",
        "values":
        [
            "Y40","Y41","Y42","Y43","Y44","Y45","Y46","Y47","Y48","Y49","Y50","Y51","Y52","Y53","Y54","Y55","Y56","Y57","Y58","Y59",
            "Y60","Y61","Y62","Y63","Y64","Y65","Y66","Y67","Y68","Y69","Y70","Y71","Y72","Y73","Y74","Y75","Y76","Y77","Y78","Y79","Y80","Y81","Y82","Y83","Y84"
        ]
    },
    {
        "name":"Sequelae of external causes of morbidity and mortality",
        "values":
        [
            "Y85","Y86","Y87","Y88","Y89"
        ]
    },
    {
        "name":"Supplementary factors related to causes of morbidity and mortality classified elsewhere",
        "values":
        [
            "Y90","Y91","Y92","Y93","Y94","Y95","Y96","Y97","Y98"
        ]
    },
    {
        "name":"Persons encountering health services for examination and investigation",
        "values":
        [
            "Z00","Z01","Z02","Z03","Z04","Z08","Z09","Z10","Z11","Z12","Z13"
        ]
    },
    {
        "name":"Persons with potential health hazards related to communicable diseases",
        "values":
        [
            "Z20","Z21","Z22","Z23","Z24","Z25","Z26","Z27","Z28","Z29"
        ]
    },
    {
        "name":"Persons encountering health services in circumstances related to reproduction",
        "values":
        [
            "Z30","Z31","Z32","Z33","Z34","Z35","Z36","Z37","Z38","Z39"
        ]
    },
    {
        "name":"Persons encountering health services for specific procedures and health care",
        "values":
        [
            "Z40","Z41","Z42","Z43","Z44","Z45","Z46","Z47","Z48","Z49","Z50","Z51","Z52","Z53","Z54"
        ]
    },
    {
        "name":"Persons with potential health hazards related to socioeconomic and psychosocial circumstances",
        "values":
        [
            "Z55","Z56","Z57","Z58","Z59","Z60","Z61","Z62","Z63","Z64","Z65"
        ]
    },
    {
        "name":"Persons encountering health services in other circumstances",
        "values":
        [
            "Z70","Z71","Z72","Z73","Z74","Z75","Z76"
        ]
    },
    {
        "name":"Persons with potential health hazards related to family and personal history and certain conditions influencing health status",
        "values":
        [
            "Z80","Z81","Z82","Z83","Z84","Z85","Z86","Z87","Z88","Z89","Z90","Z91","Z92","Z93","Z94","Z95","Z96","Z97","Z98","Z99"
        ]
    },
    {
        "name":"Provisional assignment of new diseases of uncertain etiology or emergency use",
        "values":
        [
           
        ]
    },
    {
        "name":"Resistance to antimicrobial and antineoplastic drugs",
        "values":
        [
            "U00","U01","U02","U03","U04","U05","U06","U07","U08","U09","U10","U11","U12","U13","U14","U15","U16","U17","U18","U19","U20","U21","U22","U23","U24","U25","U26","U27","U28","U29",
            "U30","U31","U32","U33","U34","U35","U36","U37","U38","U39","U40","U41","U42","U43","U44","U45","U46","U47","U48","U49"
        ]
    },
    {
        "name":"esistance to antimicrobial and antineoplastic drugs",
        "values":
        [
            "U82","U83","U84","U85"
        ]
    }
]);

