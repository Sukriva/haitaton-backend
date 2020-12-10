--liquibase formatted sql
--changeset teemu:create-schema comment:Create Louhi Database Schema

CREATE SCHEMA IF NOT EXISTS louhi;
