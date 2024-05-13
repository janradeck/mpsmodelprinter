//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2024.04.26 um 01:42:45 PM UTC 
//


package de.janradeck.mps.modelreader;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}persistence"/&gt;
 *         &lt;element ref="{}languages"/&gt;
 *         &lt;element ref="{}imports"/&gt;
 *         &lt;element ref="{}registry"/&gt;
 *         &lt;element ref="{}node" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "persistence",
    "languages",
    "imports",
    "registry",
    "node"
})
@XmlRootElement(name = "model")
public class Model {

    @XmlElement(required = true)
    protected Persistence persistence;
    @XmlElement(required = true)
    protected Languages languages;
    @XmlElement(required = true)
    protected Imports imports;
    @XmlElement(required = true)
    protected Registry registry;
    protected List<MPSNode> node;
    @XmlAttribute(name = "ref")
    protected String ref;

    /**
     * Ruft den Wert der persistence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Persistence }
     *     
     */
    public Persistence getPersistence() {
        return persistence;
    }

    /**
     * Legt den Wert der persistence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Persistence }
     *     
     */
    public void setPersistence(Persistence value) {
        this.persistence = value;
    }

    /**
     * Ruft den Wert der languages-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Languages }
     *     
     */
    public Languages getLanguages() {
        return languages;
    }

    /**
     * Legt den Wert der languages-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Languages }
     *     
     */
    public void setLanguages(Languages value) {
        this.languages = value;
    }

    /**
     * Ruft den Wert der imports-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Imports }
     *     
     */
    public Imports getImports() {
        return imports;
    }

    /**
     * Legt den Wert der imports-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Imports }
     *     
     */
    public void setImports(Imports value) {
        this.imports = value;
    }

    /**
     * Ruft den Wert der registry-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Registry }
     *     
     */
    public Registry getRegistry() {
        return registry;
    }

    /**
     * Legt den Wert der registry-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Registry }
     *     
     */
    public void setRegistry(Registry value) {
        this.registry = value;
    }

    /**
     * Gets the value of the node property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the node property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MPSNode }
     * 
     * 
     */
    public List<MPSNode> getNode() {
        if (node == null) {
            node = new ArrayList<MPSNode>();
        }
        return this.node;
    }

    /**
     * Ruft den Wert der ref-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Legt den Wert der ref-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
    }

}
