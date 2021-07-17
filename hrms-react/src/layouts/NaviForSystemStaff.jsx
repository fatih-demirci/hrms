import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu,Dropdown,Grid } from 'semantic-ui-react'
export default function NaviForSystemStaff({signOut}) {
    return (
        <div>
<Grid className="systemStaffCenter">

          <Menu.Item>
                
                <Dropdown pointing="top left" text="ad soyad">
                    <Dropdown.Menu>
                        <Dropdown.Item text="Profil" icon="info"/>
                        <Dropdown.Item onClick={signOut}  text="Çıkış Yap" icon="sign-out" />
                        
                    </Dropdown.Menu>
                </Dropdown>
            </Menu.Item>

        <Menu.Item
          name='confirm'
          as={NavLink} to="/Advertisement/Approve"
        >
          Confirm
        </Menu.Item>
        </Grid>
        </div>
    )
}
