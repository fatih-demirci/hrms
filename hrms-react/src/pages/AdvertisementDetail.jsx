import React, { useState, useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { NavLink, useParams } from 'react-router-dom'
import { toast } from 'react-toastify'
import { Button, Header, Image, Table } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'
import { addToFavorites, removeFromFavorites } from '../store/actions/favoriteActions'

export default function AdvertisementDetail() {

  let { id } = useParams()

  const [advertisement, setAdvertisement] = useState([])

  useEffect(() => {
    let advertisementService = new AdvertisementService()
    advertisementService.getJobAdvertisementById(id).then(result => setAdvertisement(result.data.data))
  }, [id])

  function formatDate(date) {
    date = String(date).split('T');
    return date[0]
  }

  const dispatch = useDispatch()
 const handleAddToFavorites=(advertisement)=> {
   dispatch(addToFavorites(advertisement))
   dispatch(removeFromFavorites({id:0}))
  }


  return (
    <div>
      {advertisement != null && advertisement.approved == true && advertisement.jobAdvertisementOpen == true ?
        <Table basic='very' celled collapsing>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>İş açıklaması</Table.HeaderCell>
              <Table.HeaderCell>İş pozisyonu</Table.HeaderCell>
              <Table.HeaderCell>Açık pozisyon</Table.HeaderCell>
              <Table.HeaderCell>Taban maaş</Table.HeaderCell>
              <Table.HeaderCell>Tavan maaş</Table.HeaderCell>
              <Table.HeaderCell>Şehir</Table.HeaderCell>
              <Table.HeaderCell>Yayınlanma tarihi</Table.HeaderCell>
              <Table.HeaderCell>Son başvuru tarihi</Table.HeaderCell>
              <Table.HeaderCell    ></Table.HeaderCell>
              <Table.HeaderCell></Table.HeaderCell>
            </Table.Row>
          </Table.Header>


          <Table.Body>
            <Table.Row>
              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.jobDescription != null ? advertisement.jobDescription  : ""}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.jobPosition != null ? advertisement.jobPosition.name : ''}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.openPositions}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.minSalary}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.maxSalary}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {advertisement.city != null ? advertisement.city.city : ''}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {formatDate(advertisement.releaseDate)}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    {formatDate(advertisement.applicationDeadline)}
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    <Button icon="arrow circle left" as={NavLink} to="/AdvertisementList/p/1"></Button>
                  </Header.Content>
                </Header>
              </Table.Cell>

              <Table.Cell>
                <Header as='h4'>
                  <Header.Content>
                    <Button onClick={() => handleAddToFavorites(advertisement)}>Favorilere Ekle</Button>
                  </Header.Content>
                </Header>
              </Table.Cell>


            </Table.Row>

          </Table.Body>






        </Table>
        : "İş ilanı bulunamadı"}

    </div>
  )
}
