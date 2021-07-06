import axios from "axios";

export default class ImageService {
    uploadImage(jobSeekerId, image){
        axios.post("http://localhost:8080/api/image/uploadImage?jobSeekerId="+jobSeekerId,image);
    }
}