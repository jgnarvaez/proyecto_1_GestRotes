import React from 'react'
import { Avatar } from 'primereact/avatar';

function Perfil(props) {
  const { name, profession, src } = props;

  return (
    <div className="user-profile">
      <Avatar image={src} shape="circle" size="medium"/>
      <div className="user-info">
        <div className="user-name">{name}</div>
        <div className="user-profession" text-align="center">{profession}</div>
      </div>
    </div>
  );
}

export default Perfil;
