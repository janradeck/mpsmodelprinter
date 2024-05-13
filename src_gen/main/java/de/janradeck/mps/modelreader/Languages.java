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
 *         &lt;element ref="{}use" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{}devkit"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "use",
    "devkit"
})
@XmlRootElement(name = "languages")
public class Languages {

    protected List<Use> use;
    @XmlElement(required = true)
    protected Devkit devkit;

    /**
     * Gets the value of the use property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the use property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Use }
     * 
     * 
     */
    public List<Use> getUse() {
        if (use == null) {
            use = new ArrayList<Use>();
        }
        return this.use;
    }

    /**
     * Ruft den Wert der devkit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Devkit }
     *     
     */
    public Devkit getDevkit() {
        return devkit;
    }

    /**
     * Legt den Wert der devkit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Devkit }
     *     
     */
    public void setDevkit(Devkit value) {
        this.devkit = value;
    }

}
