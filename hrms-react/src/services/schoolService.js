import axios from "axios";

export default class SchoolService {

    getAllSchoolSorted(jobSeekerId) { 
        return axios.post("http://localhost:8080/SchoolController/getAllSchoolSorted?id="+jobSeekerId)
    }
}