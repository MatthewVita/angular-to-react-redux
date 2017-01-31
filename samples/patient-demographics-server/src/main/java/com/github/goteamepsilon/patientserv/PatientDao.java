/**
 * Name, DOB, SSN, MaritalStat, Gender, Address, City, Post, State, Country, Phone, Email, Billing Notes, Other NOtes
 */

package com.github.goteamepsilon.patientserv;

import com.hubspot.rosetta.jdbi.BindWithRosetta;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import java.util.List;
import java.util.Optional;


@RegisterMapperFactory(RosettaMapperFactory.class)
public interface PatientDao {

  @SqlQuery("SELECT * FROM patients")
  List<Patient> getAllPatients();

  @SingleValueResult
  @SqlQuery("SELECT * FROM expenses WHERE id = :id")
  Optional<Patient> getPatientById(@Bind("id") int id);

   @GetGeneratedKeys
   @SqlUpdate("INSERT INTO patients (name, dob, ssn, maritalstatus, gender, address, city, postcode, " +
       "counmvntry, phone, email, billingnotes, patientnotes) " +
       "VALUES (:name, :dob, :ssn, :maritalstatus, :gender, :address, :city, :postcode, " +
       ":country, :phone, :email, :billingnotes, :patientnotes)")
   int insertPatient(@BindWithRosetta PatientEgg patientEgg);

  @SqlUpdate("DELETE FROM expenses WHERE id = :id")
  void deletePatientById(@Bind("id") int id);
}