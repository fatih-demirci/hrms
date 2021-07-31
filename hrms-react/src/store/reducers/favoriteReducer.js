import { useDispatch } from "react-redux";
import { toast } from "react-toastify";
import { ADD_TO_FAVORITE, REMOVE_FROM_FAVORITE } from "../actions/favoriteActions";
import { favoriteItems } from "../initialValues/favoriteItems";
import FavoriteAdvertisementService from "../../services/favoriteAdvertisementService";

const initialState = {
    favoriteItems: favoriteItems
}

export default function favoriteReducer(state = initialState, {type, payload}){

    let favoriteAdvertisementService = new FavoriteAdvertisementService()
    
    switch(type){
        case ADD_TO_FAVORITE:
            let advertisement = state.favoriteItems.find(a=>a.advertisement.id===payload.id)
            
        if(advertisement!==undefined){
            toast.error("Zaten Favorilere Ekli")
            return {
                ...state,
            }
        }else{
            favoriteAdvertisementService.add(19,payload.id)
            return {
                
                ...state,
                favoriteItems:[...state.favoriteItems,{advertisement:payload}],
                
            }
            
        }
        case REMOVE_FROM_FAVORITE:
            if(payload.id!==0){
                favoriteAdvertisementService.delete(19,payload.id)
                toast.success("İlan Favorilerden Silindi")
            }
           
            return{
                ...state,
                favoriteItems:state.favoriteItems.filter(a=>a.advertisement.id!==payload.id)
            }

        default:
            return state
        }
}