import React,{useEffect,useState} from 'react'
import CvService from '../services/cvService'
import { Card,Grid,Container } from 'semantic-ui-react'
export default function CvList() {
    const [cvs, setCvs] = useState([])

    useEffect(() => {
    let cvService = new CvService()
     cvService.getAllCvWithJobSeeker().then(result => setCvs(result.data.data))
    },[])

    return (
        <div>
              <Container className="main">
        <Grid>
          {
            
            cvs.map(cv => (
       
              <Card key={cv.id} centered raised
                image={cv.imageURL!=null?cv.imageURL:"logo192.png"}
                href= {`/CvDetail/${cv.id}`}
                header={cv.name +" " +cv.lastName}
                meta={cv.birthDay}
                description={cv.email}
              />
             
            ))
           
          }
        </Grid>
      </Container>
        </div>
    )
}
