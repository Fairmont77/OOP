import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class Main {
    public static void main(String[] args) {
        ArrayList<Medicine> medicines = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int choice = 0;
        int choice2 = 0;

        while (choice != 4) {
            System.out.println("1. Add medicine");
            System.out.println("2. Show medicines");
            System.out.println("3. Sort medicines");
            System.out.println("4. Exit");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    Medicine medicine = new Medicine();
                    System.out.println("Enter name of medicine");
                    medicine.setName(in.next());
                    System.out.println("Enter manufacturer");
                    medicine.setPharm(in.next());
                    System.out.println("Enter group");
                    medicine.setGroup(in.next());
                    System.out.println("Enter analogs");
                    medicine.setAnalogs(in.next());
                    System.out.println("Enter versions");
                    medicine.setVersions(in.next());
                    System.out.println("Enter certificate");
                    medicine.setCertificate(in.next());
                    System.out.println("Enter package");
                    medicine.setPackage(in.next());
                    System.out.println("Enter dosage");
                    medicine.setDosage(in.next());
                    medicines.add(medicine);
                    break;
                case 2:
                    for (Medicine m : medicines) {
                        System.out.println(m);
                    }
                    break;
                case 3:
                    System.out.println("1. Sort by name");
                    System.out.println("2. Sort by pharm");
                    System.out.println("3. Sort by group");
                    System.out.println("4. Sort by analogs");
                    System.out.println("5. Sort by versions");
                    System.out.println("6. Sort by certificate");
                    System.out.println("7. Sort by package");
                    System.out.println("8. Sort by dosage");
                    choice2 = in.nextInt();
                    switch (choice2) {
                        case 1:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            });
                            break;
                        case 2:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getPharm().compareTo(o2.getPharm());
                                }
                            });
                            break;
                        case 3:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getGroup().compareTo(o2.getGroup());
                                }
                            });
                            break;
                        case 4:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getAnalogs().compareTo(o2.getAnalogs());
                                }
                            });
                            break;
                        case 5:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getVersions().compareTo(o2.getVersions());
                                }
                            });
                            break;
                        case 6:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getCertificate().compareTo(o2.getCertificate());
                                }
                            });
                            break;
                        case 7:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getPackage().compareTo(o2.getPackage());
                                }
                            });
                            break;
                        case 8:
                            Collections.sort(medicines, new Comparator<Medicine>() {
                                @Override
                                public int compare(Medicine o1, Medicine o2) {
                                    return o1.getDosage().compareTo(o2.getDosage());
                                }
                            });
                            break;
                    }
                    break;
                case 4:
                    break;
            }
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("Medicines");
            document.appendChild(rootElement);
            for (Medicine m : medicines) {
                Element medicine = document.createElement("Medicine");
                rootElement.appendChild(medicine);
                Element name = document.createElement("Name");
                name.appendChild(document.createTextNode(m.getName()));
                medicine.appendChild(name);
                Element pharm = document.createElement("Pharm");
                pharm.appendChild(document.createTextNode(m.getPharm()));
                medicine.appendChild(pharm);
                Element group = document.createElement("Group");
                group.appendChild(document.createTextNode(m.getGroup()));
                medicine.appendChild(group);
                Element analogs = document.createElement("Analogs");
                analogs.appendChild(document.createTextNode(m.getAnalogs()));
                medicine.appendChild(analogs);
                Element versions = document.createElement("Versions");
                versions.appendChild(document.createTextNode(m.getVersions()));
                medicine.appendChild(versions);
                Element certificate = document.createElement("Certificate");
                certificate.appendChild(document.createTextNode(m.getCertificate()));
                medicine.appendChild(certificate);
                Element pack = document.createElement("Package");
                pack.appendChild(document.createTextNode(m.getPackage()));
                medicine.appendChild(pack);
                Element dosage = document.createElement("Dosage");
                dosage.appendChild(document.createTextNode(m.getDosage()));
                medicine.appendChild(dosage);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("medicines.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

class Medicine {
    private String name;
    private String pharm;
    private String group;
    private String analogs;
    private String versions;
    private String certificate;
    private String pack;
    private String dosage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAnalogs() {
        return analogs;
    }

    public void setAnalogs(String analogs) {
        this.analogs = analogs;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPackage() {
        return pack;
    }

    public void setPackage(String pack) {
        this.pack = pack;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group='" + group + '\'' +
                ", analogs='" + analogs + '\'' +
                ", versions='" + versions + '\'' +
                ", certificate='" + certificate + '\'' +
                ", pack='" + pack + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}