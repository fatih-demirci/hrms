import React, { useState, useEffect } from 'react'
import { Card, Container, Grid } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'

export default function Advertisement() {
  const [advertisements, setAdvertisements] = useState([])

  useEffect(() => {
    let advertisementService = new AdvertisementService()
    advertisementService.getAdvertisements().then(result => setAdvertisements(result.data.data))
  })


  return (
    <div>
      <Container className="main">
        <Grid>
          {
            advertisements.map(advertisement => (

              <Card key={advertisement.id} centered raised

                href='#card-example-link-card'
                header={advertisement.companyName}
                meta={advertisement.releaseDate + " - " + advertisement.applicationDeadline} 
                description={advertisement.positionName}
                extra={advertisement.openPositions}
              />
            ))
          }
        </Grid>
      </Container>
    </div>
  )

}
