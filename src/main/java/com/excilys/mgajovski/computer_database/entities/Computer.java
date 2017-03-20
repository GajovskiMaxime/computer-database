package com.excilys.mgajovski.computer_database.entities;

import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.utils.Utils;

/**
 * Computer entity.
 * This is the representation of a computer.
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 * @see IComputer
 */
public class Computer {

    private long id;
    private String name;
    private LocalDate introducedDate;
    private LocalDate discontinuedDate;
    private Company company;

    /**
     * Private constructor for Computer.
     * @see Computer.Builder
     */
    private Computer() {

    }

    public void setId(Long id) {
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

    public LocalDate getIntroducedDate() {
        return introducedDate;
    }

    public void setIntroducedDate(LocalDate introducedDate) {
        this.introducedDate = introducedDate;
    }

    public LocalDate getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(LocalDate discontinued) {
        this.discontinuedDate = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        Computer other = (Computer) obj;
        if (id != other.id) {
          return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class : " + this.getClass().getSimpleName() + "\n" + "\t" + "id : " + getId() + "\n" + "\t" + "name : "
                + getName() + "\n" + "\t" + "introduced date : "
                + (getIntroducedDate() == null ? Utils.UNDEFINED : getIntroducedDate()) + "\n" + "\t"
                + "discontinued date : " + (getDiscontinuedDate() == null ? Utils.UNDEFINED : getDiscontinuedDate())
                + "\n" + "\t" + "company name : " + (getCompany() == null ? Utils.UNDEFINED : getCompany().getName())
                + "\n";
    }

    /**
     * Builder Factory for Computer entity.
     * @return Computer.Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Inner Builder for the Computer entity.
     * @return a computer object.
     * @see Computer
     */
    public static class Builder {

        private Computer computer = new Computer();
        
        /**
         * Computer's builder constructor.
         */
        private Builder() {
        }

        /**
         * Computer's builder method.
         * @param id : the id of the computer
         * @return Computer.Builder
         */
        public Builder id(long id) {
            computer.id = id;
            return this;
        }

        /**
         * Computer's builder method.
         * @param name : the name of the computer.
         * @return Computer.Builder
         */
        public Builder name(String name) {
            computer.name = name;
            return this;
        }
        /**
         * Computer's builder method.
         * @param company : the company who created the computer.
         * @return Computer.Builder
         */
        public Builder company(Company company) {
            computer.company = company;
            return this;
        }

        /**
         * Computer's builder method.
         * @param introducedDate : the date where the computer is introduced
         * @return Computer.Builder
         */
        public Builder introduced(LocalDate introducedDate) {
            computer.introducedDate = introducedDate;
            return this;
        }

        /**
         * Computer's builder method.
         * @param discontinuedDate : the date where the computer is discontinued
         * @return Computer.Builder
         */
        public Builder discontinued(LocalDate discontinuedDate) {
            computer.discontinuedDate = discontinuedDate;
            return this;
        }

        /**
         * Computer's builder method.
         * @return a computer object.
         */
        public Computer build() {
            return computer;
        }

    }
}
