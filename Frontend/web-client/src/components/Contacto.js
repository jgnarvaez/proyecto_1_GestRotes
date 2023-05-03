import React from 'react'
import { Avatar } from 'primereact/avatar';

function Contacto(props) {
    const { name, number, src } = props;
  
    return (
      <div className="contact-profile">
        <Avatar image={src} shape="circle" size="medium"/>
        <div className="user-info">
          <div className="user-name">{name}</div>
          <div className="user-profession" text-align="center">{number}</div>
        </div>
      </div>
    );
  }
  
  export default Contacto;
