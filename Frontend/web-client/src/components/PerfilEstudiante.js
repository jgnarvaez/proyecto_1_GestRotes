import React from 'react'
import { Avatar } from 'primereact/avatar';

function PerfilEstudiante(props) {
    const { name, codigo, correo, src } = props;
  
    return (
      <div className="user-profile-student">
        <Avatar image={src} shape="circle" size="large"/>
        <div className="user-info-student">
          <div className="user-name-student">{name}</div>
          <div className="user-codigo">{codigo}</div>
          <div className="user-correo">{correo}</div>
        </div>
      </div>
    );
  }
  
  export default PerfilEstudiante;
