package com.excilys.mgajovski.computer_database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company entity.
 * This is the representation of a company.
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
@Entity
@Table(name ="company")
public class Company {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name="name")
    private String name;

    /**
     * Private constructor for Company.
     * @see Company.Builder
     */
    private Company() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Builder Factory for Company entity.
     * @return Company.Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
            return true;
      }
        if (obj == null) {
          return false;
        }
        if (getClass() != obj.getClass()) {
          return false;
        }
        Company other = (Company) obj;
        if (id != other.id) {
          return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class : " + this.getClass().getSimpleName() + "\n" +
                "\t" + "id : " + getId() + "\n" +
                "\t" + "name : " + getName() + "\n";
    }

    /**
     * Inner Builder for the Company entity.
     * @return a company object.
     * @see Company
     */

    public static class Builder {

        private Company company = new Company();

        /**
         * Company's builder constructor.
         */
        private Builder() {
        }

        /**
         * Company's builder method.
         * @param id : the id of the company.
         * @return Company.Builder
         */
        public Builder id(long id) {
            company.id = id;
            return this;
        }

        /**
         * Company's builder method.
         * @param name : the name of the company.
         * @return Company.Builder
         */
        public Builder name(String name) {
            company.name = name;
            return this;
        }

        /**
         * Company's builder method.
         * @return a company object.
         */
        public Company build() {
            return company;
        }
    }
}
