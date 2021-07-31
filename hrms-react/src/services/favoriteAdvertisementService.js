import axios from "axios"
import { toast } from "react-toastify";
export default class FavoriteAdvertisementService {

    add(jobSeekerId, jobAdvertisementId) {
        return axios.post(`http://localhost:8080/FavoriteJobAdvertisementController/add?jobAdvertisementId=${jobAdvertisementId}&jobSeekerId=${jobSeekerId}`).then(function (response) {

            console.log(response.data.message)
            // toast.success(response.data.message)
        })
            .catch(function (error) {
                toast.success(error.response.data)
                console.log(error.response.data)
                return error.response.data;
            });
    }

    delete(jobSeekerId,jobAdvertisementId){
        return axios.post(`http://localhost:8080/FavoriteJobAdvertisementController/delete?jobAdvertisementId=${jobAdvertisementId}&jobSeekerId=${jobSeekerId}`).then(function (response) {

            console.log(response.data.message)
            // toast.success(response.data.message)
        })
            .catch(function (error) {
                toast.success(error.response.data)
                console.log(error.response.data)
                return error.response.data;
            });
    }

}

