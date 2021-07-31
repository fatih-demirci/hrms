import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu, Dropdown, Grid } from 'semantic-ui-react'
export default function NaviForEmployer({ signOut }) {
  return (
    <div>


      <Grid className="employerCenter">

        <Menu.Item>
          <Dropdown pointing="top left" text="Şirket Adı">
            <Dropdown.Menu>
              <Dropdown.Item text="Profil" icon="info" />
              <Dropdown.Item onClick={signOut} text="Çıkış Yap" icon="sign-out" />

            </Dropdown.Menu>
          </Dropdown>
        </Menu.Item>

        <Menu.Item
          name='profile'

        >
          Profile
        </Menu.Item>

        <Menu.Item as={NavLink} to="/JobAdvertisement/Add"
          name='Add Advertisement'

        >
          Add Advertisement
        </Menu.Item>

        <Menu.Item as={NavLink} to="/CvList"
          name='view cv'

        >
          Öz Geçmişler
        </Menu.Item>

        <Menu.Item as={NavLink} to="/Advertisement/Edit"
          name='update advertisement'

        >
          İş İlanlarını Güncelle
        </Menu.Item>

      </Grid>


    </div>
  )
}
