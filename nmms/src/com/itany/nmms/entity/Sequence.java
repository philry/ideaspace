package com.itany.nmms.entity;

public class Sequence {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sequence.id
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sequence.name
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sequence.value
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    private String value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sequence.id
     *
     * @return the value of sys_sequence.id
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sequence.id
     *
     * @param id the value for sys_sequence.id
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sequence.name
     *
     * @return the value of sys_sequence.name
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sequence.name
     *
     * @param name the value for sys_sequence.name
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sequence.value
     *
     * @return the value of sys_sequence.value
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sequence.value
     *
     * @param value the value for sys_sequence.value
     *
     * @mbggenerated Fri Nov 02 09:30:44 CST 2018
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}