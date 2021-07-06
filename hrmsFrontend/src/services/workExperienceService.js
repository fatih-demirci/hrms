import axios from "axios";

export default class WorkExperienceService {
    getAllWorkExperienceSorted(jobSeekerId) {
        return axios.post("http://localhost:8080/SchoolController/getAllSchoolSorted?id=22" + jobSeekerId)
    }

}