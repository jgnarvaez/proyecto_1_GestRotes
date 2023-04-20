/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     15/04/2023 9:29:09 p. m.                     */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ACUDIENT_ACU_DIR_DIRECCIO') then
    alter table ACUDIENTE
       delete foreign key FK_ACUDIENT_ACU_DIR_DIRECCIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_DOC_ASIG_DOCE_DOCENTE') then
    alter table ASIG_DOCENTE
       delete foreign key FK_ASIG_DOC_ASIG_DOCE_DOCENTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_DOC_ASIG_DOCE_ASIGNATU') then
    alter table ASIG_DOCENTE
       delete foreign key FK_ASIG_DOC_ASIG_DOCE_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_EST_ASIG_EST_ASIGNATU') then
    alter table ASIG_EST
       delete foreign key FK_ASIG_EST_ASIG_EST_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_EST_ASIG_EST2_ESTUDIAN') then
    alter table ASIG_EST
       delete foreign key FK_ASIG_EST_ASIG_EST2_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_PRO_ASIG_PROG_PROGRAMA') then
    alter table ASIG_PROG
       delete foreign key FK_ASIG_PRO_ASIG_PROG_PROGRAMA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASIG_PRO_ASIG_PROG_ASIGNATU') then
    alter table ASIG_PROG
       delete foreign key FK_ASIG_PRO_ASIG_PROG_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_DAT_AC_FO_FORMACIO') then
    alter table DATOS_ACADEMICOS
       delete foreign key FK_DATOS_AC_DAT_AC_FO_FORMACIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_DAT_AC_PR_PRODUCCI') then
    alter table DATOS_ACADEMICOS
       delete foreign key FK_DATOS_AC_DAT_AC_PR_PRODUCCI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_EST_DAT_A_ESTUDIAN') then
    alter table DATOS_ACADEMICOS
       delete foreign key FK_DATOS_AC_EST_DAT_A_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_DAT_AC_DO_TARJETA_') then
    alter table DATOS_ACADEMICOS_DOCENTE
       delete foreign key FK_DATOS_AC_DAT_AC_DO_TARJETA_
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_ES2_DATOS_AC') then
    alter table DATOS_ACADEMICOS_DOCENTE
       delete foreign key FK_DATOS_AC_ES2_DATOS_AC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_EXP_DAT_A_EXPERIEN') then
    alter table DATOS_ACADEMICOS_DOCENTE
       delete foreign key FK_DATOS_AC_EXP_DAT_A_EXPERIEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_DAT_AC_ES_RECONOCI') then
    alter table DATOS_ACADEMICOS_ESTUDIANTE
       delete foreign key FK_DATOS_AC_DAT_AC_ES_RECONOCI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_AC_ES_DATOS_AC') then
    alter table DATOS_ACADEMICOS_ESTUDIANTE
       delete foreign key FK_DATOS_AC_ES_DATOS_AC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DATOS_GE_DTGEN_EST_ESTUDIAN') then
    alter table DATOS_GERERALES
       delete foreign key FK_DATOS_GE_DTGEN_EST_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DAT_AC_I_DAT_AC_ID_DATOS_AC') then
    alter table DAT_AC_IDM
       delete foreign key FK_DAT_AC_I_DAT_AC_ID_DATOS_AC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DAT_AC_I_DAT_AC_ID_MANEJOID') then
    alter table DAT_AC_IDM
       delete foreign key FK_DAT_AC_I_DAT_AC_ID_MANEJOID
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DEPENDEN_INS_EXP_D_INSTITUC') then
    alter table DEPENDENCIA
       delete foreign key FK_DEPENDEN_INS_EXP_D_INSTITUC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DIRECCIO_DIR_CIUDA_CIUDAD') then
    alter table DIRECCION
       delete foreign key FK_DIRECCIO_DIR_CIUDA_CIUDAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DIRECCIO_DIR_DEPT_DEPARTAM') then
    alter table DIRECCION
       delete foreign key FK_DIRECCIO_DIR_DEPT_DEPARTAM
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DOCENTE_ES_UN2_ESTUDIAN') then
    alter table DOCENTE
       delete foreign key FK_DOCENTE_ES_UN2_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DOCUMENT_ASIG_DOCU_ASIGNATU') then
    alter table DOCUMENTO
       delete foreign key FK_DOCUMENT_ASIG_DOCU_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DOCUMENT_ESCENARIO_ESCENARI') then
    alter table DOCUMENTO
       delete foreign key FK_DOCUMENT_ESCENARIO_ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESCENARI_ESCENARIO_ESTUDIAN') then
    alter table ESCENARIO_ESTUDIANTE
       delete foreign key FK_ESCENARI_ESCENARIO_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESCENARI_ESCENARIO_ESCENARI') then
    alter table ESCENARIO_ESTUDIANTE
       delete foreign key FK_ESCENARI_ESCENARIO_ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_EST_ACUDI_ACUDIENT') then
    alter table ESTUDIANTE
       delete foreign key FK_ESTUDIAN_EST_ACUDI_ACUDIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_EST_DIREC_DIRECCIO') then
    alter table ESTUDIANTE
       delete foreign key FK_ESTUDIAN_EST_DIREC_DIRECCIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_EST_NAC_LUGAR_NA') then
    alter table ESTUDIANTE
       delete foreign key FK_ESTUDIAN_EST_NAC_LUGAR_NA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_EST_PROG_PROGRAMA') then
    alter table ESTUDIANTE
       delete foreign key FK_ESTUDIAN_EST_PROG_PROGRAMA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_VER_INMU__VERIFICA') then
    alter table ESTUDIANTE
       delete foreign key FK_ESTUDIAN_VER_INMU__VERIFICA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_DT_AC_EST_DATOS_AC') then
    alter table ESTUDIANTE_PREGRADO
       delete foreign key FK_ESTUDIAN_DT_AC_EST_DATOS_AC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_EST_GRUPO_GRUPO') then
    alter table ESTUDIANTE_PREGRADO
       delete foreign key FK_ESTUDIAN_EST_GRUPO_GRUPO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ESTUDIAN_ES_UN_ESTUDIAN') then
    alter table ESTUDIANTE_PREGRADO
       delete foreign key FK_ESTUDIAN_ES_UN_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EST_VACU_EST_VACUN_ESTUDIAN') then
    alter table EST_VACUNA
       delete foreign key FK_EST_VACU_EST_VACUN_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EST_VACU_EST_VACUN_VACUNA') then
    alter table EST_VACUNA
       delete foreign key FK_EST_VACU_EST_VACUN_VACUNA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETIQUETA_ESTIQUETA_SERVICIO') then
    alter table ETIQUETA
       delete foreign key FK_ETIQUETA_ESTIQUETA_SERVICIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ETIQUETA_ETIQUETA__ESCENARI') then
    alter table ETIQUETA
       delete foreign key FK_ETIQUETA_ETIQUETA__ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EXPERIEN_EXP_DOC_DIRECCIO') then
    alter table EXPERIENCIA_DOCENTE
       delete foreign key FK_EXPERIEN_EXP_DOC_DIRECCIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EXPERIEN_EXP_DOCEN_INSTITUC') then
    alter table EXPERIENCIA_DOCENTE
       delete foreign key FK_EXPERIEN_EXP_DOCEN_INSTITUC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EXPERIEN_EXP_DOC_D_DEPENDEN') then
    alter table EXPERIENCIA_DOCENTE
       delete foreign key FK_EXPERIEN_EXP_DOC_D_DEPENDEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HORARIO_HORARIO_E_ESCENARI') then
    alter table HORARIO
       delete foreign key FK_HORARIO_HORARIO_E_ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_HORARIO_HORARIO_S_SERVICIO') then
    alter table HORARIO
       delete foreign key FK_HORARIO_HORARIO_S_SERVICIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INSTITUC_INST_EXP__DIRECCIO') then
    alter table INSTITUCION_EXP
       delete foreign key FK_INSTITUC_INST_EXP__DIRECCIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INSTITUC_INS_EXP_P_PAIS') then
    alter table INSTITUCION_EXP
       delete foreign key FK_INSTITUC_INS_EXP_P_PAIS
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LUGAR_NA_NAC_CIUDA_CIUDAD') then
    alter table LUGAR_NAC
       delete foreign key FK_LUGAR_NA_NAC_CIUDA_CIUDAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LUGAR_NA_NAC_DEPT_DEPARTAM') then
    alter table LUGAR_NAC
       delete foreign key FK_LUGAR_NA_NAC_DEPT_DEPARTAM
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MANEJOID_MANEJO_ID_IDIOMA') then
    alter table MANEJOIDIOMA
       delete foreign key FK_MANEJOID_MANEJO_ID_IDIOMA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRODUCCI_PROD_INT__CIUDAD') then
    alter table PRODUCCION_INTELECTUAL
       delete foreign key FK_PRODUCCI_PROD_INT__CIUDAD
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRUEBAS__ESC_PRUEB_ESCENARI') then
    alter table PRUEBAS_REALIZADAS
       delete foreign key FK_PRUEBAS__ESC_PRUEB_ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRUEBAS__EST_PRUEB_ESTUDIAN') then
    alter table PRUEBAS_REALIZADAS
       delete foreign key FK_PRUEBAS__EST_PRUEB_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_REQUISIT_REQUISITO_VERIFICA') then
    alter table REQUISITO_INMUNIZACION
       delete foreign key FK_REQUISIT_REQUISITO_VERIFICA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_REQUISIT_REQUISITO_REQUISIT') then
    alter table REQUISITO_INMUNIZACION
       delete foreign key FK_REQUISIT_REQUISITO_REQUISIT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_ASIG_ROTE_ASIGNATU') then
    alter table ROTE
       delete foreign key FK_ROTE_ASIG_ROTE_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_ROTE_CICL_CICLO') then
    alter table ROTE
       delete foreign key FK_ROTE_ROTE_CICL_CICLO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_ROTE_GRUP_GRUPO') then
    alter table ROTE
       delete foreign key FK_ROTE_ROTE_GRUP_GRUPO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_DOC_ROTE_DOCE_DOCENTE') then
    alter table ROTE_DOCENTE
       delete foreign key FK_ROTE_DOC_ROTE_DOCE_DOCENTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_DOC_ROTE_DOCE_ROTE') then
    alter table ROTE_DOCENTE
       delete foreign key FK_ROTE_DOC_ROTE_DOCE_ROTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_HOR_ROTE_HORA_HORARIO') then
    alter table ROTE_HORARIO
       delete foreign key FK_ROTE_HOR_ROTE_HORA_HORARIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROTE_HOR_ROTE_HORA_ROTE') then
    alter table ROTE_HORARIO
       delete foreign key FK_ROTE_HOR_ROTE_HORA_ROTE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SALUD_SE_SAL_Y_SEG_ESTUDIAN') then
    alter table SALUD_SEGURIDAD
       delete foreign key FK_SALUD_SE_SAL_Y_SEG_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TITULACI_TITULACIO_ESTUDIAN') then
    alter table TITULACION
       delete foreign key FK_TITULACI_TITULACIO_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_ASIG_TURN_ASIGNATU') then
    alter table TURNO
       delete foreign key FK_TURNO_ASIG_TURN_ASIGNATU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_TURNO_ESC_ESCENARI') then
    alter table TURNO
       delete foreign key FK_TURNO_TURNO_ESC_ESCENARI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_TURNO_ETI_ETIQUETA') then
    alter table TURNO
       delete foreign key FK_TURNO_TURNO_ETI_ETIQUETA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_TURNO_JOR_JORNADA') then
    alter table TURNO
       delete foreign key FK_TURNO_TURNO_JOR_JORNADA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_ES_TURNO_EST_ESTUDIAN') then
    alter table TURNO_ESTUDIANTE
       delete foreign key FK_TURNO_ES_TURNO_EST_ESTUDIAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TURNO_ES_TURNO_EST_TURNO') then
    alter table TURNO_ESTUDIANTE
       delete foreign key FK_TURNO_ES_TURNO_EST_TURNO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VACUNACI_ES_UNA_REQUISIT') then
    alter table VACUNACION
       delete foreign key FK_VACUNACI_ES_UNA_REQUISIT
end if;

drop index if exists ACUDIENTE.ACUDIENTE_PK;

drop table if exists ACUDIENTE;

drop index if exists ASIGNATURA.ASIGNATURA_PK;

drop table if exists ASIGNATURA;

drop index if exists ASIG_DOCENTE.ASIG_DOCENTE_FK;

drop index if exists ASIG_DOCENTE.ASIG_DOCENTE2_FK;

drop index if exists ASIG_DOCENTE.ASIG_DOCENTE_PK;

drop table if exists ASIG_DOCENTE;

drop index if exists ASIG_EST.ASIG_EST_FK;

drop index if exists ASIG_EST.ASIG_EST2_FK;

drop index if exists ASIG_EST.ASIG_EST_PK;

drop table if exists ASIG_EST;

drop index if exists ASIG_PROG.ASIG_PROG_FK;

drop index if exists ASIG_PROG.ASIG_PROG2_FK;

drop index if exists ASIG_PROG.ASIG_PROG_PK;

drop table if exists ASIG_PROG;

drop index if exists CICLO.CICLO_PK;

drop table if exists CICLO;

drop index if exists CIUDAD.CIUDAD_PK;

drop table if exists CIUDAD;

drop index if exists COORDINADOR.COORDINADOR_PK;

drop table if exists COORDINADOR;

drop index if exists DATOS_ACADEMICOS.DAT_AC_FORM_AC_FK;

drop index if exists DATOS_ACADEMICOS.DATOS_ACADEMICOS_PK;

drop table if exists DATOS_ACADEMICOS;

drop index if exists DATOS_ACADEMICOS_DOCENTE.ES2_FK;

drop index if exists DATOS_ACADEMICOS_DOCENTE.EXP_DAT_AC_DOCENTE_FK;

drop index if exists DATOS_ACADEMICOS_DOCENTE.DATOS_ACADEMICOS_DOCENTE_PK;

drop table if exists DATOS_ACADEMICOS_DOCENTE;

drop index if exists DATOS_ACADEMICOS_ESTUDIANTE.ES_FK;

drop index if exists DATOS_ACADEMICOS_ESTUDIANTE.DAT_AC_EST_REC_AC_FK;

drop index if exists DATOS_ACADEMICOS_ESTUDIANTE.DATOS_ACADEMICOS_ESTUDIANTE_PK;

drop table if exists DATOS_ACADEMICOS_ESTUDIANTE;

drop index if exists DATOS_GERERALES.DATOS_GERERALES_PK;

drop table if exists DATOS_GERERALES;

drop index if exists DAT_AC_IDM.DAT_AC_IDM_FK;

drop index if exists DAT_AC_IDM.DAT_AC_IDM2_FK;

drop index if exists DAT_AC_IDM.DAT_AC_IDM_PK;

drop table if exists DAT_AC_IDM;

drop index if exists DEPARTAMENTO.DEPARTAMENTO_PK;

drop table if exists DEPARTAMENTO;

drop index if exists DEPENDENCIA.INS_EXP_DEPENDENCIA_FK;

drop index if exists DEPENDENCIA.DEPENDENCIA_PK;

drop table if exists DEPENDENCIA;

drop index if exists DIRECCION.DIRECCION_PK;

drop table if exists DIRECCION;

drop index if exists DOCENTE.DOCENTE_PK;

drop table if exists DOCENTE;

drop index if exists DOCUMENTO.ASIG_DOCUMENTO_FK;

drop index if exists DOCUMENTO.ESCENARIO_DOCUMENTO_FK;

drop index if exists DOCUMENTO.DOCUMENTO_PK;

drop table if exists DOCUMENTO;

drop index if exists ESCENARIO_ESTUDIANTE.ESCENARIO_ESTUDIANTE_FK;

drop index if exists ESCENARIO_ESTUDIANTE.ESCENARIO_ESTUDIANTE2_FK;

drop index if exists ESCENARIO_ESTUDIANTE.ESCENARIO_ESTUDIANTE_PK;

drop table if exists ESCENARIO_ESTUDIANTE;

drop index if exists ESCENARIO_PRACTICA.ESCENARIO_PRACTICA_PK;

drop table if exists ESCENARIO_PRACTICA;

drop index if exists ESTUDIANTE.EST_PROG_FK;

drop index if exists ESTUDIANTE.EST_NAC_FK;

drop index if exists ESTUDIANTE.VER_INMU_EST_FK;

drop index if exists ESTUDIANTE.ESTUDIANTE_PK;

drop table if exists ESTUDIANTE;

drop index if exists ESTUDIANTE_PREGRADO.DT_AC_EST_FK;

drop index if exists ESTUDIANTE_PREGRADO.EST_GRUPO_FK;

drop index if exists ESTUDIANTE_PREGRADO.ESTUDIANTE_PREGRADO_PK;

drop table if exists ESTUDIANTE_PREGRADO;

drop index if exists EST_VACUNA.EST_VACUNA_FK;

drop index if exists EST_VACUNA.EST_VACUNA2_FK;

drop index if exists EST_VACUNA.EST_VACUNA_PK;

drop table if exists EST_VACUNA;

drop index if exists ETIQUETA.ESTIQUETA_SERVICIO_FK;

drop index if exists ETIQUETA.ETIQUETA_ESCENARIO_FK;

drop index if exists ETIQUETA.ETIQUETA_PK;

drop table if exists ETIQUETA;

drop index if exists EXPERIENCIA_DOCENTE.EXP_DOCENTE_INST_FK;

drop index if exists EXPERIENCIA_DOCENTE.EXPERIENCIA_DOCENTE_PK;

drop table if exists EXPERIENCIA_DOCENTE;

drop index if exists FORMACION_ACADEMICA.FORMACION_ACADEMICA_PK;

drop table if exists FORMACION_ACADEMICA;

drop index if exists GRUPO.GRUPO_PK;

drop table if exists GRUPO;

drop index if exists HORARIO.HORARIO_SERVICIO_FK;

drop index if exists HORARIO.HORARIO_ESCENARIO_FK;

drop index if exists HORARIO.HORARIO_PK;

drop table if exists HORARIO;

drop index if exists IDIOMA.IDIOMA_PK;

drop table if exists IDIOMA;

drop index if exists INSTITUCION_EXP.INS_EXP_PAIS_FK;

drop index if exists INSTITUCION_EXP.INSTITUCION_EXP_PK;

drop table if exists INSTITUCION_EXP;

drop index if exists JORNADA.JORNADA_PK;

drop table if exists JORNADA;

drop index if exists LUGAR_NAC.NAC_CIUDAD_FK;

drop index if exists LUGAR_NAC.NAC_DEPT_FK;

drop index if exists LUGAR_NAC.LUGAR_NAC_PK;

drop table if exists LUGAR_NAC;

drop index if exists MANEJOIDIOMA.MANEJO_IDM_IDIOMA_FK;

drop index if exists MANEJOIDIOMA.MANEJOIDIOMA_PK;

drop table if exists MANEJOIDIOMA;

drop index if exists PAIS.PAIS_PK;

drop table if exists PAIS;

drop index if exists PRODUCCION_INTELECTUAL.PROD_INT_CIUDAD_FK;

drop index if exists PRODUCCION_INTELECTUAL.PRODUCCION_INTELECTUAL_PK;

drop table if exists PRODUCCION_INTELECTUAL;

drop index if exists PROGRAMA.PROGRAMA_PK;

drop table if exists PROGRAMA;

drop index if exists PRUEBAS_REALIZADAS.ESC_PRUEBAS_R_FK;

drop index if exists PRUEBAS_REALIZADAS.EST_PRUEBAS_R_FK;

drop index if exists PRUEBAS_REALIZADAS.PRUEBAS_REALIZADAS_PK;

drop table if exists PRUEBAS_REALIZADAS;

drop index if exists RECONOCIMIENTO_ACADEMICO.RECONOCIMIENTO_ACADEMICO_PK;

drop table if exists RECONOCIMIENTO_ACADEMICO;

drop index if exists REQUISITO_ESCENARIO.REQUISITO_ESCENARIO_PK;

drop table if exists REQUISITO_ESCENARIO;

drop index if exists REQUISITO_INMUNIZACION.REQUISITO_INMUNIZACION_FK;

drop index if exists REQUISITO_INMUNIZACION.REQUISITO_INMUNIZACION2_FK;

drop index if exists REQUISITO_INMUNIZACION.REQUISITO_INMUNIZACION_PK;

drop table if exists REQUISITO_INMUNIZACION;

drop index if exists ROTE.ASIG_ROTE_FK;

drop index if exists ROTE.ROTE_GRUPO_FK;

drop index if exists ROTE.ROTE_CICLO_FK;

drop index if exists ROTE.ROTE_PK;

drop table if exists ROTE;

drop index if exists ROTE_DOCENTE.ROTE_DOCENTE_FK;

drop index if exists ROTE_DOCENTE.ROTE_DOCENTE2_FK;

drop index if exists ROTE_DOCENTE.ROTE_DOCENTE_PK;

drop table if exists ROTE_DOCENTE;

drop index if exists ROTE_HORARIO.ROTE_HORARIO_FK;

drop index if exists ROTE_HORARIO.ROTE_HORARIO2_FK;

drop index if exists ROTE_HORARIO.ROTE_HORARIO_PK;

drop table if exists ROTE_HORARIO;

drop index if exists SALUD_SEGURIDAD.SALUD_SEGURIDAD_PK;

drop table if exists SALUD_SEGURIDAD;

drop index if exists SERVICIO.SERVICIO_PK;

drop table if exists SERVICIO;

drop index if exists TARJETA_PROFESIONAL.TARJETA_PROFESIONAL_PK;

drop table if exists TARJETA_PROFESIONAL;

drop index if exists TITULACION.TITULACION_ESTUDIANTE_FK;

drop index if exists TITULACION.TITULACION_PK;

drop table if exists TITULACION;

drop index if exists TURNO.ASIG_TURNO_FK;

drop index if exists TURNO.TURNO_ESCENARIO_FK;

drop index if exists TURNO.TURNO_ETIQUETA_FK;

drop index if exists TURNO.TURNO_JORNADA_FK;

drop index if exists TURNO.TURNO_PK;

drop table if exists TURNO;

drop index if exists TURNO_ESTUDIANTE.TURNO_ESTUDIANTE_FK;

drop index if exists TURNO_ESTUDIANTE.TURNO_ESTUDIANTE2_FK;

drop index if exists TURNO_ESTUDIANTE.TURNO_ESTUDIANTE_PK;

drop table if exists TURNO_ESTUDIANTE;

drop index if exists VACUNA.VACUNA_PK;

drop table if exists VACUNA;

drop index if exists VACUNACION.ES_UNA_FK;

drop index if exists VACUNACION.VACUNACION_PK;

drop table if exists VACUNACION;

drop index if exists VERIFICACION_INMUNIZACION.VERIFICACION_INMUNIZACION_PK;

drop table if exists VERIFICACION_INMUNIZACION;

if exists(select 1 from sys.sysusertype where type_name='DIA') then
   drop domain DIA
end if;

if exists(select 1 from sys.sysusertype where type_name='IDIOMA_NIVEL') then
   drop domain IDIOMA_NIVEL
end if;

if exists(select 1 from sys.sysusertype where type_name='ROL') then
   drop domain ROL
end if;

if exists(select 1 from sys.sysusertype where type_name='TIPO_DOCUMENTO') then
   drop domain TIPO_DOCUMENTO
end if;

if exists(select 1 from sys.sysusertype where type_name='TIPO_REQUISITO') then
   drop domain TIPO_REQUISITO
end if;

/*==============================================================*/
/* Domain: DIA                                                  */
/*==============================================================*/
create domain DIA as char(10) 
     check (@column is null or (@column in ('Lun','Mar','Mie','Jue','Vie')));

/*==============================================================*/
/* Domain: IDIOMA_NIVEL                                         */
/*==============================================================*/
create domain IDIOMA_NIVEL as char(10) 
     check (@column is null or (@column in ('B','R','M')));

/*==============================================================*/
/* Domain: ROL                                                  */
/*==============================================================*/
create domain ROL as char(10) 
     check (@column is null or (@column in ('CP','CA')));

/*==============================================================*/
/* Domain: TIPO_DOCUMENTO                                       */
/*==============================================================*/
create domain TIPO_DOCUMENTO as char(10) 
     check (@column is null or (@column in ('W','P','X')));

/*==============================================================*/
/* Domain: TIPO_REQUISITO                                       */
/*==============================================================*/
create domain TIPO_REQUISITO as char(10) 
     check (@column is null or (@column in ('T','V')));

/*==============================================================*/
/* Table: ACUDIENTE                                             */
/*==============================================================*/
create table ACUDIENTE 
(
   ACU_ID               integer                        not null,
   DIR_ID               integer                        not null,
   ACU_NOMBRE           varchar(30)                    null,
   ACU_IDENTIFICACION   numeric                        null,
   ACU_CORREO           varchar(30)                    null,
   ACU_NUMERO           integer                        null,
   constraint PK_ACUDIENTE primary key (ACU_ID)
);

/*==============================================================*/
/* Index: ACUDIENTE_PK                                          */
/*==============================================================*/
create unique index ACUDIENTE_PK on ACUDIENTE (
ACU_ID ASC
);

/*==============================================================*/
/* Table: ASIGNATURA                                            */
/*==============================================================*/
create table ASIGNATURA 
(
   ASIG_ID              integer                        not null,
   ASIG_NOMBRE          varchar(50)                    null,
   constraint PK_ASIGNATURA primary key (ASIG_ID)
);

/*==============================================================*/
/* Index: ASIGNATURA_PK                                         */
/*==============================================================*/
create unique index ASIGNATURA_PK on ASIGNATURA (
ASIG_ID ASC
);

/*==============================================================*/
/* Table: ASIG_DOCENTE                                          */
/*==============================================================*/
create table ASIG_DOCENTE 
(
   EST_ID               integer                        not null,
   ASIG_ID              integer                        not null,
   constraint PK_ASIG_DOCENTE primary key (EST_ID, ASIG_ID)
);

/*==============================================================*/
/* Index: ASIG_DOCENTE_PK                                       */
/*==============================================================*/
create unique index ASIG_DOCENTE_PK on ASIG_DOCENTE (
EST_ID ASC,
ASIG_ID ASC
);

/*==============================================================*/
/* Index: ASIG_DOCENTE2_FK                                      */
/*==============================================================*/
create index ASIG_DOCENTE2_FK on ASIG_DOCENTE (
ASIG_ID ASC
);

/*==============================================================*/
/* Index: ASIG_DOCENTE_FK                                       */
/*==============================================================*/
create index ASIG_DOCENTE_FK on ASIG_DOCENTE (
EST_ID ASC
);

/*==============================================================*/
/* Table: ASIG_EST                                              */
/*==============================================================*/
create table ASIG_EST 
(
   EST_ID               integer                        not null,
   ASIG_ID              integer                        not null,
   constraint PK_ASIG_EST primary key (EST_ID, ASIG_ID)
);

/*==============================================================*/
/* Index: ASIG_EST_PK                                           */
/*==============================================================*/
create unique index ASIG_EST_PK on ASIG_EST (
EST_ID ASC,
ASIG_ID ASC
);

/*==============================================================*/
/* Index: ASIG_EST2_FK                                          */
/*==============================================================*/
create index ASIG_EST2_FK on ASIG_EST (
EST_ID ASC
);

/*==============================================================*/
/* Index: ASIG_EST_FK                                           */
/*==============================================================*/
create index ASIG_EST_FK on ASIG_EST (
ASIG_ID ASC
);

/*==============================================================*/
/* Table: ASIG_PROG                                             */
/*==============================================================*/
create table ASIG_PROG 
(
   PROG_ID              integer                        not null,
   ASIG_ID              integer                        not null,
   constraint PK_ASIG_PROG primary key (PROG_ID, ASIG_ID)
);

/*==============================================================*/
/* Index: ASIG_PROG_PK                                          */
/*==============================================================*/
create unique index ASIG_PROG_PK on ASIG_PROG (
PROG_ID ASC,
ASIG_ID ASC
);

/*==============================================================*/
/* Index: ASIG_PROG2_FK                                         */
/*==============================================================*/
create index ASIG_PROG2_FK on ASIG_PROG (
ASIG_ID ASC
);

/*==============================================================*/
/* Index: ASIG_PROG_FK                                          */
/*==============================================================*/
create index ASIG_PROG_FK on ASIG_PROG (
PROG_ID ASC
);

/*==============================================================*/
/* Table: CICLO                                                 */
/*==============================================================*/
create table CICLO 
(
   CIC_ID               integer                        not null,
   CIC_FECHA_INICIO     date                           null,
   CIC_FECHA_FIN        date                           null,
   constraint PK_CICLO primary key (CIC_ID)
);

/*==============================================================*/
/* Index: CICLO_PK                                              */
/*==============================================================*/
create unique index CICLO_PK on CICLO (
CIC_ID ASC
);

/*==============================================================*/
/* Table: CIUDAD                                                */
/*==============================================================*/
create table CIUDAD 
(
   CIU_ID               integer                        not null,
   CIU_NOMBRE           varchar(30)                    null,
   constraint PK_CIUDAD primary key (CIU_ID)
);

/*==============================================================*/
/* Index: CIUDAD_PK                                             */
/*==============================================================*/
create unique index CIUDAD_PK on CIUDAD (
CIU_ID ASC
);

/*==============================================================*/
/* Table: COORDINADOR                                           */
/*==============================================================*/
create table COORDINADOR 
(
   COO_ID               integer                        not null,
   COO_NOMBRES          varchar(50)                    null,
   COO_APELLIDOS        varchar(50)                    null,
   COO_CORREO           varchar(30)                    null,
   COO_CLAVE            varchar(20)                    null,
   COO_ROL              ROL                            null,
   COO_FOTO_PERFIL      long binary                    null,
   constraint PK_COORDINADOR primary key (COO_ID)
);

/*==============================================================*/
/* Index: COORDINADOR_PK                                        */
/*==============================================================*/
create unique index COORDINADOR_PK on COORDINADOR (
COO_ID ASC
);

/*==============================================================*/
/* Table: DATOS_ACADEMICOS                                      */
/*==============================================================*/
create table DATOS_ACADEMICOS 
(
   DAT_AC_ID            integer                        not null,
   EST_ID               integer                        not null,
   F_AC_ID              integer                        not null,
   PD_IN_ID             integer                        not null,
   DAT_AC_CODIGO        integer                        null,
   DAT_AC_OBSERVACIONES varchar(150)                   null,
   constraint PK_DATOS_ACADEMICOS primary key (DAT_AC_ID)
);

/*==============================================================*/
/* Index: DATOS_ACADEMICOS_PK                                   */
/*==============================================================*/
create unique index DATOS_ACADEMICOS_PK on DATOS_ACADEMICOS (
DAT_AC_ID ASC
);

/*==============================================================*/
/* Index: DAT_AC_FORM_AC_FK                                     */
/*==============================================================*/
create index DAT_AC_FORM_AC_FK on DATOS_ACADEMICOS (
F_AC_ID ASC
);

/*==============================================================*/
/* Table: DATOS_ACADEMICOS_DOCENTE                              */
/*==============================================================*/
create table DATOS_ACADEMICOS_DOCENTE 
(
   DAT_AC_ID            integer                        not null,
   DADOC_ID             integer                        not null,
   EST_ID               integer                        not null,
   F_AC_ID              integer                        not null,
   PD_IN_ID             integer                        not null,
   DAT_AC_CODIGO        integer                        null,
   DAT_AC_OBSERVACIONES varchar(150)                   null,
   EX_DC_ID             integer                        not null,
   TJP_ID               integer                        not null,
   DADOC_ESCALAFON_DOCENTE varchar(20)                    null,
   DADOC_TIPO_VINCULACION varchar(30)                    null,
   DADOC_CARGO_ACAD     varchar(20)                    null,
   constraint PK_DATOS_ACADEMICOS_DOCENTE primary key (DAT_AC_ID, DADOC_ID)
);

/*==============================================================*/
/* Index: DATOS_ACADEMICOS_DOCENTE_PK                           */
/*==============================================================*/
create unique index DATOS_ACADEMICOS_DOCENTE_PK on DATOS_ACADEMICOS_DOCENTE (
DAT_AC_ID ASC,
DADOC_ID ASC
);

/*==============================================================*/
/* Index: EXP_DAT_AC_DOCENTE_FK                                 */
/*==============================================================*/
create index EXP_DAT_AC_DOCENTE_FK on DATOS_ACADEMICOS_DOCENTE (
EX_DC_ID ASC
);

/*==============================================================*/
/* Index: ES2_FK                                                */
/*==============================================================*/
create index ES2_FK on DATOS_ACADEMICOS_DOCENTE (
DAT_AC_ID ASC
);

/*==============================================================*/
/* Table: DATOS_ACADEMICOS_ESTUDIANTE                           */
/*==============================================================*/
create table DATOS_ACADEMICOS_ESTUDIANTE 
(
   DAT_AC_ID            integer                        not null,
   DA_EST_ID            integer                        not null,
   EST_ID               integer                        not null,
   F_AC_ID              integer                        not null,
   PD_IN_ID             integer                        not null,
   DAT_AC_CODIGO        integer                        null,
   DAT_AC_OBSERVACIONES varchar(150)                   null,
   REC_ID               integer                        not null,
   DA_EST_FATLAS        integer                        null,
   constraint PK_DATOS_ACADEMICOS_ESTUDIANTE primary key (DAT_AC_ID, DA_EST_ID)
);

/*==============================================================*/
/* Index: DATOS_ACADEMICOS_ESTUDIANTE_PK                        */
/*==============================================================*/
create unique index DATOS_ACADEMICOS_ESTUDIANTE_PK on DATOS_ACADEMICOS_ESTUDIANTE (
DAT_AC_ID ASC,
DA_EST_ID ASC
);

/*==============================================================*/
/* Index: DAT_AC_EST_REC_AC_FK                                  */
/*==============================================================*/
create index DAT_AC_EST_REC_AC_FK on DATOS_ACADEMICOS_ESTUDIANTE (
REC_ID ASC
);

/*==============================================================*/
/* Index: ES_FK                                                 */
/*==============================================================*/
create index ES_FK on DATOS_ACADEMICOS_ESTUDIANTE (
DAT_AC_ID ASC
);

/*==============================================================*/
/* Table: DATOS_GERERALES                                       */
/*==============================================================*/
create table DATOS_GERERALES 
(
   DTGEN_ID             integer                        not null,
   EST_ID               integer                        not null,
   DTGEN_OBSVERVACIONES varchar(150)                   null,
   constraint PK_DATOS_GERERALES primary key (DTGEN_ID)
);

/*==============================================================*/
/* Index: DATOS_GERERALES_PK                                    */
/*==============================================================*/
create unique index DATOS_GERERALES_PK on DATOS_GERERALES (
DTGEN_ID ASC
);

/*==============================================================*/
/* Table: DAT_AC_IDM                                            */
/*==============================================================*/
create table DAT_AC_IDM 
(
   DAT_AC_ID            integer                        not null,
   MID_ID               integer                        not null,
   constraint PK_DAT_AC_IDM primary key (DAT_AC_ID, MID_ID)
);

/*==============================================================*/
/* Index: DAT_AC_IDM_PK                                         */
/*==============================================================*/
create unique index DAT_AC_IDM_PK on DAT_AC_IDM (
DAT_AC_ID ASC,
MID_ID ASC
);

/*==============================================================*/
/* Index: DAT_AC_IDM2_FK                                        */
/*==============================================================*/
create index DAT_AC_IDM2_FK on DAT_AC_IDM (
MID_ID ASC
);

/*==============================================================*/
/* Index: DAT_AC_IDM_FK                                         */
/*==============================================================*/
create index DAT_AC_IDM_FK on DAT_AC_IDM (
DAT_AC_ID ASC
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO 
(
   DEPT_ID              integer                        not null,
   DEPT_NOMBRE          varchar(30)                    null,
   constraint PK_DEPARTAMENTO primary key (DEPT_ID)
);

/*==============================================================*/
/* Index: DEPARTAMENTO_PK                                       */
/*==============================================================*/
create unique index DEPARTAMENTO_PK on DEPARTAMENTO (
DEPT_ID ASC
);

/*==============================================================*/
/* Table: DEPENDENCIA                                           */
/*==============================================================*/
create table DEPENDENCIA 
(
   DEPD_ID              integer                        not null,
   INS_ID               integer                        not null,
   DEPD_NOMBRE          varchar(30)                    null,
   constraint PK_DEPENDENCIA primary key (DEPD_ID)
);

/*==============================================================*/
/* Index: DEPENDENCIA_PK                                        */
/*==============================================================*/
create unique index DEPENDENCIA_PK on DEPENDENCIA (
DEPD_ID ASC
);

/*==============================================================*/
/* Index: INS_EXP_DEPENDENCIA_FK                                */
/*==============================================================*/
create index INS_EXP_DEPENDENCIA_FK on DEPENDENCIA (
INS_ID ASC
);

/*==============================================================*/
/* Table: DIRECCION                                             */
/*==============================================================*/
create table DIRECCION 
(
   DIR_ID               integer                        not null,
   CIU_ID               integer                        not null,
   DEPT_ID              integer                        not null,
   DIR_DIRECCION        varchar(40)                    null,
   DIR_BARRIO           varchar(20)                    null,
   constraint PK_DIRECCION primary key (DIR_ID)
);

/*==============================================================*/
/* Index: DIRECCION_PK                                          */
/*==============================================================*/
create unique index DIRECCION_PK on DIRECCION (
DIR_ID ASC
);

/*==============================================================*/
/* Table: DOCENTE                                               */
/*==============================================================*/
create table DOCENTE 
(
   EST_ID               integer                        not null,
   ACU_ID               integer                        not null,
   DIR_ID               integer                        not null,
   V_INM_ID             integer                        not null,
   NAC_ID               integer                        not null,
   PROG_ID              integer                        not null,
   EST_NOMBRE           varchar(30)                    null,
   EST_IDENTIFICACION   numeric                        null,
   EST_TIPO_SANGRE      varchar(2)                     null,
   EST_CORREO           varchar(30)                    null,
   EST_FECHA_NAC        date                           null,
   EST_CELULAR          numeric                        null,
   EST_DNI_FRONTAL      long binary                    null,
   EST_DNI_REVERSO      long binary                    null,
   EST_CARNET_FRONTAL   long binary                    null,
   EST_CARNET_REVERSO   long binary                    null,
   EST_FOTO_PERFIL      long binary                    null,
   EST_CARNET_VACUNAS   long binary                    null,
   constraint PK_DOCENTE primary key (EST_ID)
);

/*==============================================================*/
/* Index: DOCENTE_PK                                            */
/*==============================================================*/
create unique index DOCENTE_PK on DOCENTE (
EST_ID ASC
);

/*==============================================================*/
/* Table: DOCUMENTO                                             */
/*==============================================================*/
create table DOCUMENTO 
(
   DOC_ID               integer                        not null,
   ESC_ID               integer                        not null,
   ASIG_ID              integer                        not null,
   DOC_TIPO             TIPO_DOCUMENTO                 null,
   DOC_FECHA_VIGENCIA   date                           null,
   DOC_ARCHIVO          long binary                    null,
   constraint PK_DOCUMENTO primary key (DOC_ID)
);

/*==============================================================*/
/* Index: DOCUMENTO_PK                                          */
/*==============================================================*/
create unique index DOCUMENTO_PK on DOCUMENTO (
DOC_ID ASC
);

/*==============================================================*/
/* Index: ESCENARIO_DOCUMENTO_FK                                */
/*==============================================================*/
create index ESCENARIO_DOCUMENTO_FK on DOCUMENTO (
ESC_ID ASC
);

/*==============================================================*/
/* Index: ASIG_DOCUMENTO_FK                                     */
/*==============================================================*/
create index ASIG_DOCUMENTO_FK on DOCUMENTO (
ASIG_ID ASC
);

/*==============================================================*/
/* Table: ESCENARIO_ESTUDIANTE                                  */
/*==============================================================*/
create table ESCENARIO_ESTUDIANTE 
(
   EST_ID               integer                        not null,
   ESC_ID               integer                        not null,
   constraint PK_ESCENARIO_ESTUDIANTE primary key (EST_ID, ESC_ID)
);

/*==============================================================*/
/* Index: ESCENARIO_ESTUDIANTE_PK                               */
/*==============================================================*/
create unique index ESCENARIO_ESTUDIANTE_PK on ESCENARIO_ESTUDIANTE (
EST_ID ASC,
ESC_ID ASC
);

/*==============================================================*/
/* Index: ESCENARIO_ESTUDIANTE2_FK                              */
/*==============================================================*/
create index ESCENARIO_ESTUDIANTE2_FK on ESCENARIO_ESTUDIANTE (
ESC_ID ASC
);

/*==============================================================*/
/* Index: ESCENARIO_ESTUDIANTE_FK                               */
/*==============================================================*/
create index ESCENARIO_ESTUDIANTE_FK on ESCENARIO_ESTUDIANTE (
EST_ID ASC
);

/*==============================================================*/
/* Table: ESCENARIO_PRACTICA                                    */
/*==============================================================*/
create table ESCENARIO_PRACTICA 
(
   ESC_ID               integer                        not null,
   ESC_NOMBRE           varchar(30)                    null,
   constraint PK_ESCENARIO_PRACTICA primary key (ESC_ID)
);

/*==============================================================*/
/* Index: ESCENARIO_PRACTICA_PK                                 */
/*==============================================================*/
create unique index ESCENARIO_PRACTICA_PK on ESCENARIO_PRACTICA (
ESC_ID ASC
);

/*==============================================================*/
/* Table: ESTUDIANTE                                            */
/*==============================================================*/
create table ESTUDIANTE 
(
   EST_ID               integer                        not null,
   ACU_ID               integer                        not null,
   DIR_ID               integer                        not null,
   V_INM_ID             integer                        not null,
   NAC_ID               integer                        not null,
   PROG_ID              integer                        not null,
   EST_NOMBRE           varchar(30)                    null,
   EST_IDENTIFICACION   numeric                        null,
   EST_TIPO_SANGRE      varchar(2)                     null,
   EST_CORREO           varchar(30)                    null,
   EST_FECHA_NAC        date                           null,
   EST_CELULAR          numeric                        null,
   EST_DNI_FRONTAL      long binary                    null,
   EST_DNI_REVERSO      long binary                    null,
   EST_CARNET_FRONTAL   long binary                    null,
   EST_CARNET_REVERSO   long binary                    null,
   EST_FOTO_PERFIL      long binary                    null,
   EST_CARNET_VACUNAS   long binary                    null,
   constraint PK_ESTUDIANTE primary key (EST_ID)
);

/*==============================================================*/
/* Index: ESTUDIANTE_PK                                         */
/*==============================================================*/
create unique index ESTUDIANTE_PK on ESTUDIANTE (
EST_ID ASC
);

/*==============================================================*/
/* Index: VER_INMU_EST_FK                                       */
/*==============================================================*/
create index VER_INMU_EST_FK on ESTUDIANTE (
V_INM_ID ASC
);

/*==============================================================*/
/* Index: EST_NAC_FK                                            */
/*==============================================================*/
create index EST_NAC_FK on ESTUDIANTE (
NAC_ID ASC
);

/*==============================================================*/
/* Index: EST_PROG_FK                                           */
/*==============================================================*/
create index EST_PROG_FK on ESTUDIANTE (
PROG_ID ASC
);

/*==============================================================*/
/* Table: ESTUDIANTE_PREGRADO                                   */
/*==============================================================*/
create table ESTUDIANTE_PREGRADO 
(
   EST_ID               integer                        not null,
   ACU_ID               integer                        not null,
   DIR_ID               integer                        not null,
   V_INM_ID             integer                        not null,
   NAC_ID               integer                        not null,
   PROG_ID              integer                        not null,
   EST_NOMBRE           varchar(30)                    null,
   EST_IDENTIFICACION   numeric                        null,
   EST_TIPO_SANGRE      varchar(2)                     null,
   EST_CORREO           varchar(30)                    null,
   EST_FECHA_NAC        date                           null,
   EST_CELULAR          numeric                        null,
   EST_DNI_FRONTAL      long binary                    null,
   EST_DNI_REVERSO      long binary                    null,
   EST_CARNET_FRONTAL   long binary                    null,
   EST_CARNET_REVERSO   long binary                    null,
   EST_FOTO_PERFIL      long binary                    null,
   EST_CARNET_VACUNAS   long binary                    null,
   GRU_ID               integer                        not null,
   DAT_AC_ID            integer                        not null,
   DA_EST_ID            integer                        not null,
   constraint PK_ESTUDIANTE_PREGRADO primary key (EST_ID)
);

/*==============================================================*/
/* Index: ESTUDIANTE_PREGRADO_PK                                */
/*==============================================================*/
create unique index ESTUDIANTE_PREGRADO_PK on ESTUDIANTE_PREGRADO (
EST_ID ASC
);

/*==============================================================*/
/* Index: EST_GRUPO_FK                                          */
/*==============================================================*/
create index EST_GRUPO_FK on ESTUDIANTE_PREGRADO (
GRU_ID ASC
);

/*==============================================================*/
/* Index: DT_AC_EST_FK                                          */
/*==============================================================*/
create index DT_AC_EST_FK on ESTUDIANTE_PREGRADO (
DAT_AC_ID ASC,
DA_EST_ID ASC
);

/*==============================================================*/
/* Table: EST_VACUNA                                            */
/*==============================================================*/
create table EST_VACUNA 
(
   EST_ID               integer                        not null,
   VAC_ID               integer                        not null,
   constraint PK_EST_VACUNA primary key (EST_ID, VAC_ID)
);

/*==============================================================*/
/* Index: EST_VACUNA_PK                                         */
/*==============================================================*/
create unique index EST_VACUNA_PK on EST_VACUNA (
EST_ID ASC,
VAC_ID ASC
);

/*==============================================================*/
/* Index: EST_VACUNA2_FK                                        */
/*==============================================================*/
create index EST_VACUNA2_FK on EST_VACUNA (
VAC_ID ASC
);

/*==============================================================*/
/* Index: EST_VACUNA_FK                                         */
/*==============================================================*/
create index EST_VACUNA_FK on EST_VACUNA (
EST_ID ASC
);

/*==============================================================*/
/* Table: ETIQUETA                                              */
/*==============================================================*/
create table ETIQUETA 
(
   ETI_ID               integer                        not null,
   ESC_ID               integer                        not null,
   SERV_ID              integer                        not null,
   ETI_NOMBRE           varchar(30)                    null,
   constraint PK_ETIQUETA primary key (ETI_ID)
);

/*==============================================================*/
/* Index: ETIQUETA_PK                                           */
/*==============================================================*/
create unique index ETIQUETA_PK on ETIQUETA (
ETI_ID ASC
);

/*==============================================================*/
/* Index: ETIQUETA_ESCENARIO_FK                                 */
/*==============================================================*/
create index ETIQUETA_ESCENARIO_FK on ETIQUETA (
ESC_ID ASC
);

/*==============================================================*/
/* Index: ESTIQUETA_SERVICIO_FK                                 */
/*==============================================================*/
create index ESTIQUETA_SERVICIO_FK on ETIQUETA (
SERV_ID ASC
);

/*==============================================================*/
/* Table: EXPERIENCIA_DOCENTE                                   */
/*==============================================================*/
create table EXPERIENCIA_DOCENTE 
(
   EX_DC_ID             integer                        not null,
   DEPD_ID              integer                        null,
   DIR_ID               integer                        not null,
   INS_ID               integer                        not null,
   EX_DC_FECHA_INICIO   date                           null,
   EX_DC_ESTADO         smallint                       null,
   EX_DC_CARGO          varchar(30)                    null,
   EX_DC_CERTIFICADO    long binary                    null,
   constraint PK_EXPERIENCIA_DOCENTE primary key (EX_DC_ID)
);

/*==============================================================*/
/* Index: EXPERIENCIA_DOCENTE_PK                                */
/*==============================================================*/
create unique index EXPERIENCIA_DOCENTE_PK on EXPERIENCIA_DOCENTE (
EX_DC_ID ASC
);

/*==============================================================*/
/* Index: EXP_DOCENTE_INST_FK                                   */
/*==============================================================*/
create index EXP_DOCENTE_INST_FK on EXPERIENCIA_DOCENTE (
INS_ID ASC
);

/*==============================================================*/
/* Table: FORMACION_ACADEMICA                                   */
/*==============================================================*/
create table FORMACION_ACADEMICA 
(
   F_AC_ID              integer                        not null,
   F_AC_PERIODO_DURACION numeric                        null,
   F_AC_FECHA_FIN       date                           null,
   F_AC_DIPLOMA         long binary                    null,
   F_AC_INSTITUCION     varchar(30)                    null,
   constraint PK_FORMACION_ACADEMICA primary key (F_AC_ID)
);

/*==============================================================*/
/* Index: FORMACION_ACADEMICA_PK                                */
/*==============================================================*/
create unique index FORMACION_ACADEMICA_PK on FORMACION_ACADEMICA (
F_AC_ID ASC
);

/*==============================================================*/
/* Table: GRUPO                                                 */
/*==============================================================*/
create table GRUPO 
(
   GRU_ID               integer                        not null,
   GRU_NUMERO           integer                        null,
   constraint PK_GRUPO primary key (GRU_ID)
);

/*==============================================================*/
/* Index: GRUPO_PK                                              */
/*==============================================================*/
create unique index GRUPO_PK on GRUPO (
GRU_ID ASC
);

/*==============================================================*/
/* Table: HORARIO                                               */
/*==============================================================*/
create table HORARIO 
(
   HOR_ID               integer                        not null,
   ESC_ID               integer                        not null,
   SERV_ID              integer                        not null,
   HOR_DIA              DIA                            null,
   HOR_NOMBRE           varchar(30)                    null,
   HOR_HORA_INICIO      time                           null,
   HOR_HORA_FIN         time                           null,
   constraint PK_HORARIO primary key (HOR_ID)
);

/*==============================================================*/
/* Index: HORARIO_PK                                            */
/*==============================================================*/
create unique index HORARIO_PK on HORARIO (
HOR_ID ASC
);

/*==============================================================*/
/* Index: HORARIO_ESCENARIO_FK                                  */
/*==============================================================*/
create index HORARIO_ESCENARIO_FK on HORARIO (
ESC_ID ASC
);

/*==============================================================*/
/* Index: HORARIO_SERVICIO_FK                                   */
/*==============================================================*/
create index HORARIO_SERVICIO_FK on HORARIO (
SERV_ID ASC
);

/*==============================================================*/
/* Table: IDIOMA                                                */
/*==============================================================*/
create table IDIOMA 
(
   IDM_ID               integer                        not null,
   IDM_NOMBRE           varchar(30)                    null,
   constraint PK_IDIOMA primary key (IDM_ID)
);

/*==============================================================*/
/* Index: IDIOMA_PK                                             */
/*==============================================================*/
create unique index IDIOMA_PK on IDIOMA (
IDM_ID ASC
);

/*==============================================================*/
/* Table: INSTITUCION_EXP                                       */
/*==============================================================*/
create table INSTITUCION_EXP 
(
   INS_ID               integer                        not null,
   DIR_ID               integer                        not null,
   PAIS_ID              integer                        not null,
   INS_NOMBRE           varchar(30)                    null,
   INS_CORREO           varchar(30)                    null,
   INS_TELEFONO         integer                        null,
   constraint PK_INSTITUCION_EXP primary key (INS_ID)
);

/*==============================================================*/
/* Index: INSTITUCION_EXP_PK                                    */
/*==============================================================*/
create unique index INSTITUCION_EXP_PK on INSTITUCION_EXP (
INS_ID ASC
);

/*==============================================================*/
/* Index: INS_EXP_PAIS_FK                                       */
/*==============================================================*/
create index INS_EXP_PAIS_FK on INSTITUCION_EXP (
PAIS_ID ASC
);

/*==============================================================*/
/* Table: JORNADA                                               */
/*==============================================================*/
create table JORNADA 
(
   JOR_ID               integer                        not null,
   JOR_JORNADA          varchar(20)                    null,
   JOR_HORA_INICIO      time                           null,
   JOR_HORA_FIN         time                           null,
   constraint PK_JORNADA primary key (JOR_ID)
);

/*==============================================================*/
/* Index: JORNADA_PK                                            */
/*==============================================================*/
create unique index JORNADA_PK on JORNADA (
JOR_ID ASC
);

/*==============================================================*/
/* Table: LUGAR_NAC                                             */
/*==============================================================*/
create table LUGAR_NAC 
(
   NAC_ID               integer                        not null,
   DEPT_ID              integer                        not null,
   CIU_ID               integer                        not null,
   constraint PK_LUGAR_NAC primary key (NAC_ID)
);

/*==============================================================*/
/* Index: LUGAR_NAC_PK                                          */
/*==============================================================*/
create unique index LUGAR_NAC_PK on LUGAR_NAC (
NAC_ID ASC
);

/*==============================================================*/
/* Index: NAC_DEPT_FK                                           */
/*==============================================================*/
create index NAC_DEPT_FK on LUGAR_NAC (
DEPT_ID ASC
);

/*==============================================================*/
/* Index: NAC_CIUDAD_FK                                         */
/*==============================================================*/
create index NAC_CIUDAD_FK on LUGAR_NAC (
CIU_ID ASC
);

/*==============================================================*/
/* Table: MANEJOIDIOMA                                          */
/*==============================================================*/
create table MANEJOIDIOMA 
(
   MID_ID               integer                        not null,
   IDM_ID               integer                        not null,
   MID_HABLA            IDIOMA_NIVEL                   null,
   MID_ESCRITURA        IDIOMA_NIVEL                   null,
   MID_LEE              IDIOMA_NIVEL                   null,
   constraint PK_MANEJOIDIOMA primary key (MID_ID)
);

/*==============================================================*/
/* Index: MANEJOIDIOMA_PK                                       */
/*==============================================================*/
create unique index MANEJOIDIOMA_PK on MANEJOIDIOMA (
MID_ID ASC
);

/*==============================================================*/
/* Index: MANEJO_IDM_IDIOMA_FK                                  */
/*==============================================================*/
create index MANEJO_IDM_IDIOMA_FK on MANEJOIDIOMA (
IDM_ID ASC
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS 
(
   PAIS_ID              integer                        not null,
   PAIS_NOMBRE          varchar(30)                    null,
   constraint PK_PAIS primary key (PAIS_ID)
);

/*==============================================================*/
/* Index: PAIS_PK                                               */
/*==============================================================*/
create unique index PAIS_PK on PAIS (
PAIS_ID ASC
);

/*==============================================================*/
/* Table: PRODUCCION_INTELECTUAL                                */
/*==============================================================*/
create table PRODUCCION_INTELECTUAL 
(
   PD_IN_ID             integer                        not null,
   CIU_ID               integer                        not null,
   PD_IN_DESCRIPCION    varchar(150)                   null,
   PD_IN_FINALIZADO     smallint                       null,
   constraint PK_PRODUCCION_INTELECTUAL primary key (PD_IN_ID)
);

/*==============================================================*/
/* Index: PRODUCCION_INTELECTUAL_PK                             */
/*==============================================================*/
create unique index PRODUCCION_INTELECTUAL_PK on PRODUCCION_INTELECTUAL (
PD_IN_ID ASC
);

/*==============================================================*/
/* Index: PROD_INT_CIUDAD_FK                                    */
/*==============================================================*/
create index PROD_INT_CIUDAD_FK on PRODUCCION_INTELECTUAL (
CIU_ID ASC
);

/*==============================================================*/
/* Table: PROGRAMA                                              */
/*==============================================================*/
create table PROGRAMA 
(
   PROG_ID              integer                        not null,
   PROG_NOMBRE          varchar(30)                    null,
   constraint PK_PROGRAMA primary key (PROG_ID)
);

/*==============================================================*/
/* Index: PROGRAMA_PK                                           */
/*==============================================================*/
create unique index PROGRAMA_PK on PROGRAMA (
PROG_ID ASC
);

/*==============================================================*/
/* Table: PRUEBAS_REALIZADAS                                    */
/*==============================================================*/
create table PRUEBAS_REALIZADAS 
(
   PRLZ_ID              integer                        not null,
   EST_ID               integer                        not null,
   ESC_ID               integer                        not null,
   PRLZ_ESTADO          smallint                       null,
   constraint PK_PRUEBAS_REALIZADAS primary key (PRLZ_ID)
);

/*==============================================================*/
/* Index: PRUEBAS_REALIZADAS_PK                                 */
/*==============================================================*/
create unique index PRUEBAS_REALIZADAS_PK on PRUEBAS_REALIZADAS (
PRLZ_ID ASC
);

/*==============================================================*/
/* Index: EST_PRUEBAS_R_FK                                      */
/*==============================================================*/
create index EST_PRUEBAS_R_FK on PRUEBAS_REALIZADAS (
EST_ID ASC
);

/*==============================================================*/
/* Index: ESC_PRUEBAS_R_FK                                      */
/*==============================================================*/
create index ESC_PRUEBAS_R_FK on PRUEBAS_REALIZADAS (
ESC_ID ASC
);

/*==============================================================*/
/* Table: RECONOCIMIENTO_ACADEMICO                              */
/*==============================================================*/
create table RECONOCIMIENTO_ACADEMICO 
(
   REC_ID               integer                        not null,
   constraint PK_RECONOCIMIENTO_ACADEMICO primary key (REC_ID)
);

/*==============================================================*/
/* Index: RECONOCIMIENTO_ACADEMICO_PK                           */
/*==============================================================*/
create unique index RECONOCIMIENTO_ACADEMICO_PK on RECONOCIMIENTO_ACADEMICO (
REC_ID ASC
);

/*==============================================================*/
/* Table: REQUISITO_ESCENARIO                                   */
/*==============================================================*/
create table REQUISITO_ESCENARIO 
(
   REQ_ID               integer                        not null,
   REQ_NOMBRE           varchar(30)                    null,
   REQ_ES_TITULACION    smallint                       null
   	constraint CKC_REQ_ES_TITULACION_REQUISIT check (REQ_ES_TITULACION is null or (REQ_ES_TITULACION in (T,V))),
   REQ_ESTADO           smallint                       null,
   constraint PK_REQUISITO_ESCENARIO primary key (REQ_ID)
);

/*==============================================================*/
/* Index: REQUISITO_ESCENARIO_PK                                */
/*==============================================================*/
create unique index REQUISITO_ESCENARIO_PK on REQUISITO_ESCENARIO (
REQ_ID ASC
);

/*==============================================================*/
/* Table: REQUISITO_INMUNIZACION                                */
/*==============================================================*/
create table REQUISITO_INMUNIZACION 
(
   V_INM_ID             integer                        not null,
   REQ_ID               integer                        not null,
   constraint PK_REQUISITO_INMUNIZACION primary key (V_INM_ID, REQ_ID)
);

/*==============================================================*/
/* Index: REQUISITO_INMUNIZACION_PK                             */
/*==============================================================*/
create unique index REQUISITO_INMUNIZACION_PK on REQUISITO_INMUNIZACION (
V_INM_ID ASC,
REQ_ID ASC
);

/*==============================================================*/
/* Index: REQUISITO_INMUNIZACION2_FK                            */
/*==============================================================*/
create index REQUISITO_INMUNIZACION2_FK on REQUISITO_INMUNIZACION (
REQ_ID ASC
);

/*==============================================================*/
/* Index: REQUISITO_INMUNIZACION_FK                             */
/*==============================================================*/
create index REQUISITO_INMUNIZACION_FK on REQUISITO_INMUNIZACION (
V_INM_ID ASC
);

/*==============================================================*/
/* Table: ROTE                                                  */
/*==============================================================*/
create table ROTE 
(
   ROTE_ID              integer                        not null,
   CIC_ID               integer                        not null,
   GRU_ID               integer                        not null,
   ASIG_ID              integer                        not null,
   constraint PK_ROTE primary key (ROTE_ID)
);

/*==============================================================*/
/* Index: ROTE_PK                                               */
/*==============================================================*/
create unique index ROTE_PK on ROTE (
ROTE_ID ASC
);

/*==============================================================*/
/* Index: ROTE_CICLO_FK                                         */
/*==============================================================*/
create index ROTE_CICLO_FK on ROTE (
CIC_ID ASC
);

/*==============================================================*/
/* Index: ROTE_GRUPO_FK                                         */
/*==============================================================*/
create index ROTE_GRUPO_FK on ROTE (
GRU_ID ASC
);

/*==============================================================*/
/* Index: ASIG_ROTE_FK                                          */
/*==============================================================*/
create index ASIG_ROTE_FK on ROTE (
ASIG_ID ASC
);

/*==============================================================*/
/* Table: ROTE_DOCENTE                                          */
/*==============================================================*/
create table ROTE_DOCENTE 
(
   EST_ID               integer                        not null,
   ROTE_ID              integer                        not null,
   constraint PK_ROTE_DOCENTE primary key (EST_ID, ROTE_ID)
);

/*==============================================================*/
/* Index: ROTE_DOCENTE_PK                                       */
/*==============================================================*/
create unique index ROTE_DOCENTE_PK on ROTE_DOCENTE (
EST_ID ASC,
ROTE_ID ASC
);

/*==============================================================*/
/* Index: ROTE_DOCENTE2_FK                                      */
/*==============================================================*/
create index ROTE_DOCENTE2_FK on ROTE_DOCENTE (
ROTE_ID ASC
);

/*==============================================================*/
/* Index: ROTE_DOCENTE_FK                                       */
/*==============================================================*/
create index ROTE_DOCENTE_FK on ROTE_DOCENTE (
EST_ID ASC
);

/*==============================================================*/
/* Table: ROTE_HORARIO                                          */
/*==============================================================*/
create table ROTE_HORARIO 
(
   HOR_ID               integer                        not null,
   ROTE_ID              integer                        not null,
   constraint PK_ROTE_HORARIO primary key (HOR_ID, ROTE_ID)
);

/*==============================================================*/
/* Index: ROTE_HORARIO_PK                                       */
/*==============================================================*/
create unique index ROTE_HORARIO_PK on ROTE_HORARIO (
HOR_ID ASC,
ROTE_ID ASC
);

/*==============================================================*/
/* Index: ROTE_HORARIO2_FK                                      */
/*==============================================================*/
create index ROTE_HORARIO2_FK on ROTE_HORARIO (
ROTE_ID ASC
);

/*==============================================================*/
/* Index: ROTE_HORARIO_FK                                       */
/*==============================================================*/
create index ROTE_HORARIO_FK on ROTE_HORARIO (
HOR_ID ASC
);

/*==============================================================*/
/* Table: SALUD_SEGURIDAD                                       */
/*==============================================================*/
create table SALUD_SEGURIDAD 
(
   SAL_ID               integer                        not null,
   EST_ID               integer                        not null,
   SAL_DOC_EPS          long binary                    null,
   SAL_DOC_ARL          long binary                    null,
   SAL_NOMBRE_EPS       varchar(30)                    null,
   SAL_REGIMEN          varchar(30)                    null,
   SAL_FECHA_AFILIACION date                           null,
   SAL_FECHA_ACTUALIZACION date                           null,
   SAL_FECHA_ACT_CERTIFICADO date                           null,
   SAL_OBSERVACIONES    varchar(150)                   null,
   constraint PK_SALUD_SEGURIDAD primary key (SAL_ID)
);

/*==============================================================*/
/* Index: SALUD_SEGURIDAD_PK                                    */
/*==============================================================*/
create unique index SALUD_SEGURIDAD_PK on SALUD_SEGURIDAD (
SAL_ID ASC
);

/*==============================================================*/
/* Table: SERVICIO                                              */
/*==============================================================*/
create table SERVICIO 
(
   SERV_ID              integer                        not null,
   SERV_NOMBRE          char(10)                       null,
   constraint PK_SERVICIO primary key (SERV_ID)
);

/*==============================================================*/
/* Index: SERVICIO_PK                                           */
/*==============================================================*/
create unique index SERVICIO_PK on SERVICIO (
SERV_ID ASC
);

/*==============================================================*/
/* Table: TARJETA_PROFESIONAL                                   */
/*==============================================================*/
create table TARJETA_PROFESIONAL 
(
   TJP_ID               integer                        not null,
   DAT_AC_ID            integer                        not null,
   TJP_TARJETA_FRONTAL  long binary                    null,
   TJP_TARJETA_REVERSO  long binary                    null,
   TJP_RESOLUCION       long binary                    null,
   TJP_CONSTANCIA       long binary                    null,
   TJP_OBSERVACIONES    varchar(150)                   null,
   constraint PK_TARJETA_PROFESIONAL primary key (TJP_ID)
);

/*==============================================================*/
/* Index: TARJETA_PROFESIONAL_PK                                */
/*==============================================================*/
create unique index TARJETA_PROFESIONAL_PK on TARJETA_PROFESIONAL (
TJP_ID ASC
);

/*==============================================================*/
/* Table: TITULACION                                            */
/*==============================================================*/
create table TITULACION 
(
   TITU_ID              integer                        not null,
   EST_ID               integer                        not null,
   TITU_DETALLES        varchar(50)                    null,
   TITU_DOCUMENTO       long binary                    null,
   constraint PK_TITULACION primary key (TITU_ID)
);

/*==============================================================*/
/* Index: TITULACION_PK                                         */
/*==============================================================*/
create unique index TITULACION_PK on TITULACION (
TITU_ID ASC
);

/*==============================================================*/
/* Index: TITULACION_ESTUDIANTE_FK                              */
/*==============================================================*/
create index TITULACION_ESTUDIANTE_FK on TITULACION (
EST_ID ASC
);

/*==============================================================*/
/* Table: TURNO                                                 */
/*==============================================================*/
create table TURNO 
(
   TURNO_ID             integer                        not null,
   JOR_ID               integer                        not null,
   ETI_ID               integer                        not null,
   ESC_ID               integer                        not null,
   ASIG_ID              integer                        not null,
   TURNO_FECHA          date                           null,
   constraint PK_TURNO primary key (TURNO_ID)
);

/*==============================================================*/
/* Index: TURNO_PK                                              */
/*==============================================================*/
create unique index TURNO_PK on TURNO (
TURNO_ID ASC
);

/*==============================================================*/
/* Index: TURNO_JORNADA_FK                                      */
/*==============================================================*/
create index TURNO_JORNADA_FK on TURNO (
JOR_ID ASC
);

/*==============================================================*/
/* Index: TURNO_ETIQUETA_FK                                     */
/*==============================================================*/
create index TURNO_ETIQUETA_FK on TURNO (
ETI_ID ASC
);

/*==============================================================*/
/* Index: TURNO_ESCENARIO_FK                                    */
/*==============================================================*/
create index TURNO_ESCENARIO_FK on TURNO (
ESC_ID ASC
);

/*==============================================================*/
/* Index: ASIG_TURNO_FK                                         */
/*==============================================================*/
create index ASIG_TURNO_FK on TURNO (
ASIG_ID ASC
);

/*==============================================================*/
/* Table: TURNO_ESTUDIANTE                                      */
/*==============================================================*/
create table TURNO_ESTUDIANTE 
(
   EST_ID               integer                        not null,
   TURNO_ID             integer                        not null,
   constraint PK_TURNO_ESTUDIANTE primary key (EST_ID, TURNO_ID)
);

/*==============================================================*/
/* Index: TURNO_ESTUDIANTE_PK                                   */
/*==============================================================*/
create unique index TURNO_ESTUDIANTE_PK on TURNO_ESTUDIANTE (
EST_ID ASC,
TURNO_ID ASC
);

/*==============================================================*/
/* Index: TURNO_ESTUDIANTE2_FK                                  */
/*==============================================================*/
create index TURNO_ESTUDIANTE2_FK on TURNO_ESTUDIANTE (
TURNO_ID ASC
);

/*==============================================================*/
/* Index: TURNO_ESTUDIANTE_FK                                   */
/*==============================================================*/
create index TURNO_ESTUDIANTE_FK on TURNO_ESTUDIANTE (
EST_ID ASC
);

/*==============================================================*/
/* Table: VACUNA                                                */
/*==============================================================*/
create table VACUNA 
(
   VAC_ID               integer                        not null,
   VAC_NOMBRE           varchar(30)                    null,
   VAC_NUM_DOSIS        integer                        null,
   VAC_LOTE             integer                        null,
   VAC_FECHA            date                           null,
   constraint PK_VACUNA primary key (VAC_ID)
);

/*==============================================================*/
/* Index: VACUNA_PK                                             */
/*==============================================================*/
create unique index VACUNA_PK on VACUNA (
VAC_ID ASC
);

/*==============================================================*/
/* Table: VACUNACION                                            */
/*==============================================================*/
create table VACUNACION 
(
   REQ_ID               integer                        not null,
   VCN_ID               integer                        not null,
   REQ_NOMBRE           varchar(30)                    null,
   REQ_ES_TITULACION    smallint                       null
   	constraint CKC_REQ_ES_TITULACION_VACUNACI check (REQ_ES_TITULACION is null or (REQ_ES_TITULACION in (T,V))),
   REQ_ESTADO           smallint                       null,
   VCN_DOSIS_REQUERIDAS numeric(1)                     null,
   constraint PK_VACUNACION primary key (REQ_ID, VCN_ID)
);

/*==============================================================*/
/* Index: VACUNACION_PK                                         */
/*==============================================================*/
create unique index VACUNACION_PK on VACUNACION (
REQ_ID ASC,
VCN_ID ASC
);

/*==============================================================*/
/* Index: ES_UNA_FK                                             */
/*==============================================================*/
create index ES_UNA_FK on VACUNACION (
REQ_ID ASC
);

/*==============================================================*/
/* Table: VERIFICACION_INMUNIZACION                             */
/*==============================================================*/
create table VERIFICACION_INMUNIZACION 
(
   V_INM_ID             integer                        not null,
   V_INM_OBSERVACIONES  varchar(150)                   null,
   constraint PK_VERIFICACION_INMUNIZACION primary key (V_INM_ID)
);

/*==============================================================*/
/* Index: VERIFICACION_INMUNIZACION_PK                          */
/*==============================================================*/
create unique index VERIFICACION_INMUNIZACION_PK on VERIFICACION_INMUNIZACION (
V_INM_ID ASC
);

alter table ACUDIENTE
   add constraint FK_ACUDIENT_ACU_DIR_DIRECCIO foreign key (DIR_ID)
      references DIRECCION (DIR_ID)
      on update restrict
      on delete restrict;

alter table ASIG_DOCENTE
   add constraint FK_ASIG_DOC_ASIG_DOCE_DOCENTE foreign key (EST_ID)
      references DOCENTE (EST_ID)
      on update restrict
      on delete restrict;

alter table ASIG_DOCENTE
   add constraint FK_ASIG_DOC_ASIG_DOCE_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table ASIG_EST
   add constraint FK_ASIG_EST_ASIG_EST_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table ASIG_EST
   add constraint FK_ASIG_EST_ASIG_EST2_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE_PREGRADO (EST_ID)
      on update restrict
      on delete restrict;

alter table ASIG_PROG
   add constraint FK_ASIG_PRO_ASIG_PROG_PROGRAMA foreign key (PROG_ID)
      references PROGRAMA (PROG_ID)
      on update restrict
      on delete restrict;

alter table ASIG_PROG
   add constraint FK_ASIG_PRO_ASIG_PROG_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS
   add constraint FK_DATOS_AC_DAT_AC_FO_FORMACIO foreign key (F_AC_ID)
      references FORMACION_ACADEMICA (F_AC_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS
   add constraint FK_DATOS_AC_DAT_AC_PR_PRODUCCI foreign key (PD_IN_ID)
      references PRODUCCION_INTELECTUAL (PD_IN_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS
   add constraint FK_DATOS_AC_EST_DAT_A_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS_DOCENTE
   add constraint FK_DATOS_AC_DAT_AC_DO_TARJETA_ foreign key (TJP_ID)
      references TARJETA_PROFESIONAL (TJP_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS_DOCENTE
   add constraint FK_DATOS_AC_ES2_DATOS_AC foreign key (DAT_AC_ID)
      references DATOS_ACADEMICOS (DAT_AC_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS_DOCENTE
   add constraint FK_DATOS_AC_EXP_DAT_A_EXPERIEN foreign key (EX_DC_ID)
      references EXPERIENCIA_DOCENTE (EX_DC_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS_ESTUDIANTE
   add constraint FK_DATOS_AC_DAT_AC_ES_RECONOCI foreign key (REC_ID)
      references RECONOCIMIENTO_ACADEMICO (REC_ID)
      on update restrict
      on delete restrict;

alter table DATOS_ACADEMICOS_ESTUDIANTE
   add constraint FK_DATOS_AC_ES_DATOS_AC foreign key (DAT_AC_ID)
      references DATOS_ACADEMICOS (DAT_AC_ID)
      on update restrict
      on delete restrict;

alter table DATOS_GERERALES
   add constraint FK_DATOS_GE_DTGEN_EST_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table DAT_AC_IDM
   add constraint FK_DAT_AC_I_DAT_AC_ID_DATOS_AC foreign key (DAT_AC_ID)
      references DATOS_ACADEMICOS (DAT_AC_ID)
      on update restrict
      on delete restrict;

alter table DAT_AC_IDM
   add constraint FK_DAT_AC_I_DAT_AC_ID_MANEJOID foreign key (MID_ID)
      references MANEJOIDIOMA (MID_ID)
      on update restrict
      on delete restrict;

alter table DEPENDENCIA
   add constraint FK_DEPENDEN_INS_EXP_D_INSTITUC foreign key (INS_ID)
      references INSTITUCION_EXP (INS_ID)
      on update restrict
      on delete restrict;

alter table DIRECCION
   add constraint FK_DIRECCIO_DIR_CIUDA_CIUDAD foreign key (CIU_ID)
      references CIUDAD (CIU_ID)
      on update restrict
      on delete restrict;

alter table DIRECCION
   add constraint FK_DIRECCIO_DIR_DEPT_DEPARTAM foreign key (DEPT_ID)
      references DEPARTAMENTO (DEPT_ID)
      on update restrict
      on delete restrict;

alter table DOCENTE
   add constraint FK_DOCENTE_ES_UN2_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table DOCUMENTO
   add constraint FK_DOCUMENT_ASIG_DOCU_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table DOCUMENTO
   add constraint FK_DOCUMENT_ESCENARIO_ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table ESCENARIO_ESTUDIANTE
   add constraint FK_ESCENARI_ESCENARIO_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table ESCENARIO_ESTUDIANTE
   add constraint FK_ESCENARI_ESCENARIO_ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_EST_ACUDI_ACUDIENT foreign key (ACU_ID)
      references ACUDIENTE (ACU_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_EST_DIREC_DIRECCIO foreign key (DIR_ID)
      references DIRECCION (DIR_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_EST_NAC_LUGAR_NA foreign key (NAC_ID)
      references LUGAR_NAC (NAC_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_EST_PROG_PROGRAMA foreign key (PROG_ID)
      references PROGRAMA (PROG_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE
   add constraint FK_ESTUDIAN_VER_INMU__VERIFICA foreign key (V_INM_ID)
      references VERIFICACION_INMUNIZACION (V_INM_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE_PREGRADO
   add constraint FK_ESTUDIAN_DT_AC_EST_DATOS_AC foreign key (DAT_AC_ID, DA_EST_ID)
      references DATOS_ACADEMICOS_ESTUDIANTE (DAT_AC_ID, DA_EST_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE_PREGRADO
   add constraint FK_ESTUDIAN_EST_GRUPO_GRUPO foreign key (GRU_ID)
      references GRUPO (GRU_ID)
      on update restrict
      on delete restrict;

alter table ESTUDIANTE_PREGRADO
   add constraint FK_ESTUDIAN_ES_UN_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table EST_VACUNA
   add constraint FK_EST_VACU_EST_VACUN_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table EST_VACUNA
   add constraint FK_EST_VACU_EST_VACUN_VACUNA foreign key (VAC_ID)
      references VACUNA (VAC_ID)
      on update restrict
      on delete restrict;

alter table ETIQUETA
   add constraint FK_ETIQUETA_ESTIQUETA_SERVICIO foreign key (SERV_ID)
      references SERVICIO (SERV_ID)
      on update restrict
      on delete restrict;

alter table ETIQUETA
   add constraint FK_ETIQUETA_ETIQUETA__ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table EXPERIENCIA_DOCENTE
   add constraint FK_EXPERIEN_EXP_DOC_DIRECCIO foreign key (DIR_ID)
      references DIRECCION (DIR_ID)
      on update restrict
      on delete restrict;

alter table EXPERIENCIA_DOCENTE
   add constraint FK_EXPERIEN_EXP_DOCEN_INSTITUC foreign key (INS_ID)
      references INSTITUCION_EXP (INS_ID)
      on update restrict
      on delete restrict;

alter table EXPERIENCIA_DOCENTE
   add constraint FK_EXPERIEN_EXP_DOC_D_DEPENDEN foreign key (DEPD_ID)
      references DEPENDENCIA (DEPD_ID)
      on update restrict
      on delete restrict;

alter table HORARIO
   add constraint FK_HORARIO_HORARIO_E_ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table HORARIO
   add constraint FK_HORARIO_HORARIO_S_SERVICIO foreign key (SERV_ID)
      references SERVICIO (SERV_ID)
      on update restrict
      on delete restrict;

alter table INSTITUCION_EXP
   add constraint FK_INSTITUC_INST_EXP__DIRECCIO foreign key (DIR_ID)
      references DIRECCION (DIR_ID)
      on update restrict
      on delete restrict;

alter table INSTITUCION_EXP
   add constraint FK_INSTITUC_INS_EXP_P_PAIS foreign key (PAIS_ID)
      references PAIS (PAIS_ID)
      on update restrict
      on delete restrict;

alter table LUGAR_NAC
   add constraint FK_LUGAR_NA_NAC_CIUDA_CIUDAD foreign key (CIU_ID)
      references CIUDAD (CIU_ID)
      on update restrict
      on delete restrict;

alter table LUGAR_NAC
   add constraint FK_LUGAR_NA_NAC_DEPT_DEPARTAM foreign key (DEPT_ID)
      references DEPARTAMENTO (DEPT_ID)
      on update restrict
      on delete restrict;

alter table MANEJOIDIOMA
   add constraint FK_MANEJOID_MANEJO_ID_IDIOMA foreign key (IDM_ID)
      references IDIOMA (IDM_ID)
      on update restrict
      on delete restrict;

alter table PRODUCCION_INTELECTUAL
   add constraint FK_PRODUCCI_PROD_INT__CIUDAD foreign key (CIU_ID)
      references CIUDAD (CIU_ID)
      on update restrict
      on delete restrict;

alter table PRUEBAS_REALIZADAS
   add constraint FK_PRUEBAS__ESC_PRUEB_ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table PRUEBAS_REALIZADAS
   add constraint FK_PRUEBAS__EST_PRUEB_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table REQUISITO_INMUNIZACION
   add constraint FK_REQUISIT_REQUISITO_VERIFICA foreign key (V_INM_ID)
      references VERIFICACION_INMUNIZACION (V_INM_ID)
      on update restrict
      on delete restrict;

alter table REQUISITO_INMUNIZACION
   add constraint FK_REQUISIT_REQUISITO_REQUISIT foreign key (REQ_ID)
      references REQUISITO_ESCENARIO (REQ_ID)
      on update restrict
      on delete restrict;

alter table ROTE
   add constraint FK_ROTE_ASIG_ROTE_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table ROTE
   add constraint FK_ROTE_ROTE_CICL_CICLO foreign key (CIC_ID)
      references CICLO (CIC_ID)
      on update restrict
      on delete restrict;

alter table ROTE
   add constraint FK_ROTE_ROTE_GRUP_GRUPO foreign key (GRU_ID)
      references GRUPO (GRU_ID)
      on update restrict
      on delete restrict;

alter table ROTE_DOCENTE
   add constraint FK_ROTE_DOC_ROTE_DOCE_DOCENTE foreign key (EST_ID)
      references DOCENTE (EST_ID)
      on update restrict
      on delete restrict;

alter table ROTE_DOCENTE
   add constraint FK_ROTE_DOC_ROTE_DOCE_ROTE foreign key (ROTE_ID)
      references ROTE (ROTE_ID)
      on update restrict
      on delete restrict;

alter table ROTE_HORARIO
   add constraint FK_ROTE_HOR_ROTE_HORA_HORARIO foreign key (HOR_ID)
      references HORARIO (HOR_ID)
      on update restrict
      on delete restrict;

alter table ROTE_HORARIO
   add constraint FK_ROTE_HOR_ROTE_HORA_ROTE foreign key (ROTE_ID)
      references ROTE (ROTE_ID)
      on update restrict
      on delete restrict;

alter table SALUD_SEGURIDAD
   add constraint FK_SALUD_SE_SAL_Y_SEG_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table TITULACION
   add constraint FK_TITULACI_TITULACIO_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE (EST_ID)
      on update restrict
      on delete restrict;

alter table TURNO
   add constraint FK_TURNO_ASIG_TURN_ASIGNATU foreign key (ASIG_ID)
      references ASIGNATURA (ASIG_ID)
      on update restrict
      on delete restrict;

alter table TURNO
   add constraint FK_TURNO_TURNO_ESC_ESCENARI foreign key (ESC_ID)
      references ESCENARIO_PRACTICA (ESC_ID)
      on update restrict
      on delete restrict;

alter table TURNO
   add constraint FK_TURNO_TURNO_ETI_ETIQUETA foreign key (ETI_ID)
      references ETIQUETA (ETI_ID)
      on update restrict
      on delete restrict;

alter table TURNO
   add constraint FK_TURNO_TURNO_JOR_JORNADA foreign key (JOR_ID)
      references JORNADA (JOR_ID)
      on update restrict
      on delete restrict;

alter table TURNO_ESTUDIANTE
   add constraint FK_TURNO_ES_TURNO_EST_ESTUDIAN foreign key (EST_ID)
      references ESTUDIANTE_PREGRADO (EST_ID)
      on update restrict
      on delete restrict;

alter table TURNO_ESTUDIANTE
   add constraint FK_TURNO_ES_TURNO_EST_TURNO foreign key (TURNO_ID)
      references TURNO (TURNO_ID)
      on update restrict
      on delete restrict;

alter table VACUNACION
   add constraint FK_VACUNACI_ES_UNA_REQUISIT foreign key (REQ_ID)
      references REQUISITO_ESCENARIO (REQ_ID)
      on update restrict
      on delete restrict;

