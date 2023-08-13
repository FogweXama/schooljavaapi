package repositories;

public interface IGeneralRepository {

    static final String SQL_FIND_ALL_SSTTL = "SELECT subjectid, sequenceid, teacherid, tslid, leveldivisionid FROM public.tbl_subject_level_term_teacher";
    static final String SQL_FIND_BY_TSLID_SSTTL = "SELECT subjectid, sequenceid, teacherid, tslid, leveldivisionid FROM public.tbl_subject_level_term_teacher where tslid=?";
    static final String SQL_CREATE_SSTTL = "INSERT INTO public.tbl_subject_level_term_teacher(subjectid, sequenceid, teacherid, tslid, leveldivisionid) VALUES (?, ?, ?, NEXTVAL('tbl_subject_level_term_teacher_SEQ'), ?)";
    static final String SQL_UPDATE_SSTTL = "UPDATE public.tbl_subject_level_term_teacher SET subjectid=?, sequenceid=?, teacherid=?, tslid=?, leveldivisionid=? WHERE tslid=?";
    static final String SQL_DELETE_SSTTL = "DELETE FROM public.tbl_subject_level_term_teacher WHERE tslid=?";
    static final String SQL_DELETE_ALL_SSTTL= "DELETE FROM public.tbl_subject_level_term_teacher";

    static final String SQL_FIND_ALL_DIVISIONSEQ = "SELECT leveldivisionid, sequenceid FROM public.tblleveldivision_sequence";
    static final String SQL_FIND_BY_ID_DIVISIONSEQ = "SELECT leveldivisionid, sequenceid FROM public.tblleveldivision_sequence where leveldivisionid=?";
    static final String SQL_CREATE_DIVISIONSEQ = "INSERT INTO public.tblleveldivision_sequence(leveldivisionid, sequenceid) VALUES (?, ?)";
    static final String SQL_UPDATE_DIVISIONSEQ = "UPDATE public.tblleveldivision_sequence SET leveldivisionid=?, sequenceid=? WHERE leveldivisionid=?";
    static final String SQL_DELETE_DIVISIONSEQ = "DELETE FROM public.tblleveldivision_sequence WHERE leveldivisionid=?";
    static final String SQL_DELETE_ALL_DIVISIONSEQ= "DELETE FROM public.tblleveldivision_sequence";

    static final String SQL_FIND_ALL_STUDENTASS = "SELECT choiceid, assignmentid, score FROM public.tblstudent_assignmentscore";
    static final String SQL_FIND_BY_ID_STUDENTASS = "SELECT choiceid, assignmentid, score FROM public.tblstudent_assignmentscore where choiceid=?";
    static final String SQL_CREATE_STUDENTASS = "INSERT INTO public.tblstudent_assignmentscore(choiceid, assignmentid, score) VALUES (?,?,?)";
    static final String SQL_UPDATE_STUDENTASS = "UPDATE public.tblstudent_assignmentscore SET choiceid=?, assignmentid=?, score=? WHERE choiceid=?";
    static final String SQL_DELETE_STUDENTASS = "DELETE FROM public.tblstudent_assignmentscore WHERE choiceid=?";
    static final String SQL_DELETE_ALL_STUDENTASS= "DELETE FROM public.tblstudent_assignmentscore";

    static final String SQL_FIND_ALL_STUDENTEX = "SELECT choiceid, examid, mark, grade FROM public.tblstudent_exammark";
    static final String SQL_FIND_BY_ID_STUDENTEX = "SELECT choiceid, examid, mark, grade FROM public.tblstudent_exammark where choiceid=?";
    static final String SQL_CREATE_STUDENTEX = "INSERT INTO public.tblstudent_exammark(choiceid, examid, mark, grade) VALUES (?, ?, ?, ?)";
    static final String SQL_UPDATE_STUDENTEX = "UPDATE public.tblstudent_exammark SET choiceid=?, examid=?, mark=?, grade=? WHERE choiceid=?";
    static final String SQL_DELETE_STUDENTEX = "DELETE FROM public.tblstudent_exammark WHERE choiceid=?";
    static final String SQL_DELETE_ALL_STUDENTEX= "DELETE FROM public.tblstudent_exammark";

    static final String SQL_FIND_ALL_STUDENTSUB = "SELECT choiceid, studentid, tslid FROM public.tblstudent_subjectchosen";
    static final String SQL_FIND_BY_ID_STUDENTSUB = "SELECT choiceid, studentid, tslid FROM public.tblstudent_subjectchosen where choiceid=?";
    static final String SQL_CREATE_STUDENTSUB = "INSERT INTO public.tblstudent_subjectchosen(choiceid, studentid, tslid) VALUES (NEXTVAL('tblstudent_subjectchosen_SEQ'),?,?)";
    static final String SQL_UPDATE_STUDENTSUB = "UPDATE public.tblstudent_subjectchosen SET choiceid=?, studentid=?, tslid=? WHERE choiceid=?";
    static final String SQL_DELETE_STUDENTSUB = "DELETE FROM public.tblstudent_subjectchosen WHERE choiceid=?";
    static final String SQL_DELETE_ALL_STUDENTSUB= "DELETE FROM public.tblstudent_subjectchosen";

    static final String SQL_FIND_ALL_SUBTEACH = "SELECT subjectid, teacherid FROM public.tblsubject_teacher";
    static final String SQL_FIND_BY_ID_SUBTEACH = "SELECT subjectid, teacherid FROM public.tblsubject_teacher where subjectid=?";
    static final String SQL_CREATE_SUBTEACH = "INSERT INTO public.tblsubject_teacher(subjectid, teacherid) VALUES (?,?)";
    static final String SQL_UPDATE_SUBTEACH = "UPDATE public.tblsubject_teacher SET subjectid=?, teacherid=? WHERE subjectid=?";
    static final String SQL_DELETE_SUBTEACH = "DELETE FROM public.tblsubject_teacher WHERE subjectid=?";
    static final String SQL_DELETE_ALL_SUBTEACH= "DELETE FROM public.tblsubject_teacher";

    static final String SQL_FIND_ALL_UND = "SELECT subjectid, startdate, enddate, academicyname FROM public.tblOther";
    static final String SQL_FIND_BY_ID_UND = "SELECT subjectid, startdate, enddate, academicyname FROM public.tblOther where subjectid=?";
    static final String SQL_CREATE_UND = "INSERT INTO public.tblOther(subjectid, startdate, enddate, academicyname) VALUES (NEXTVAL('tblOther_SEQ'))";
    static final String SQL_UPDATE_UND = "UPDATE public.tblOther SET subjectid=?, startdate=?, enddate=?, academicyname=? WHERE subjectid=?";
    static final String SQL_DELETE_UND = "DELETE FROM public.tblOther WHERE subjectid=?";
    static final String SQL_DELETE_ALL_UND= "DELETE FROM public.tblOther";
}
