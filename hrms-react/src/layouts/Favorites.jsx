import React, { useState, useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Button, Dropdown, Grid, Label, Menu } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'
import { addToFavorites, removeFromFavorites } from '../store/actions/favoriteActions'
import JobSeekerService from '../services/jobSeekerService'
import FavoriteAdvertisementService from '../services/favoriteAdvertisementService'
import FavoriteItems from '../store/initialValues/favoriteItems'
import { toast } from 'react-toastify'
import { NavLink } from 'react-router-dom'

export default function Favorites() {

    function formatDate(date) {
        date = String(date).split('T');
        return date[0]
      }

    const { favoriteItems } = useSelector(state => state.favorite)

    const dispatch = useDispatch()
    const handleRemoveFromFavorites = (advertisement) => {
        dispatch(removeFromFavorites(advertisement))
    }
    const handleAddToFavorites = (advertisement) => {
        console.log(advertisement)
        dispatch(addToFavorites(advertisement))
        dispatch(removeFromFavorites({ jobPosition: { name: "Hiç Favorilere Eklenmiş İş İlanı Yok" }, id: 0 }))
    }

    let advertisementService = new AdvertisementService()
    let jobSeekerService = new JobSeekerService()

    const [advertisement, setAdvertisement] = useState([])
    const [jobSeeker, setJobSeeker] = useState([])
    const [favoriteJobAdvertisements, setFavoriteJobAdvertisements] = useState([])


    useEffect(() => {
        jobSeekerService.getJobSeekerById(19).then(result => setJobSeeker(result.data.data))

    }, [])

    useEffect(() => {

        if(jobSeeker!==null && jobSeeker.favoriteJobAdvertisement!==null){
            setFavoriteJobAdvertisements(jobSeeker.favoriteJobAdvertisement)
        }
       
        
    }, [jobSeeker])

    useEffect(() => {

        favoriteJobAdvertisements?.map((favoriteJobAdvertisement) => (
            advertisementService.getJobAdvertisementById(favoriteJobAdvertisement.jobAdvertisementId).then(result => handleAddToFavorites(result.data.data))
        ))

    }, [favoriteJobAdvertisements])


    return (

        <div>

            <Grid >

                <Dropdown item text='Favori İlanlar' >
                    <Dropdown.Menu >

                        {
                            favoriteItems.map((favoriteItem) => (
                                <Dropdown.Item key={favoriteItem.advertisement.id} as={NavLink} to={`/AdvertisementList/${favoriteItem.advertisement.id}`} >
                                    
                                    <Label >
                                        {favoriteItem.advertisement.openPositions}
                                    </Label>
                                    {favoriteItem.advertisement.jobPosition?.name}


                                    <Label >
                                        {favoriteItem.advertisement.minSalary +" - "+favoriteItem.advertisement.maxSalary}
                                    </Label>

                                    <Label >
                                        {favoriteItem.advertisement.city?.city}
                                    </Label>
                                    <Label >
                                        {formatDate(favoriteItem.advertisement.applicationDeadline )}
                                    </Label>

                                    {favoriteItem.advertisement.id !== 0 ?
                                        <Button icon="x" onClick={() => handleRemoveFromFavorites(favoriteItem.advertisement)}></Button>
                                        :
                                        null
                                    }


                                </Dropdown.Item>

                            ))

                        }

                    </Dropdown.Menu>
                </Dropdown>



            </Grid>





        </div>
    )
}
