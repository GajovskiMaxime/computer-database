package com.excilys.mgajovski.computer_database.entities;

/**
 * Company entity.
 * This is the representation of a company.
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class Company {

    private Long id;
    private String name;

    /**
     * Private constructor for Company.
     * @see Company.Builder
     */
    private Company() {

    }

    public Long getId() {
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
        public Builder id(Long id) {
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
