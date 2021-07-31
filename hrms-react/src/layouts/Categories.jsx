import React from 'react'
import { Menu } from 'semantic-ui-react'
import { NavLink } from 'react-router-dom'
export default function Categories() {
    return (
        <div>
            <Menu pointing vertical inverted>
                <Menu.Item as={NavLink} to="/"  name="Ana Sayfa"/>
                <Menu.Item as={NavLink} to="/AdvertisementList/p/1"  name="İş İlanları"/>
            </Menu>
        </div>
    )
}
