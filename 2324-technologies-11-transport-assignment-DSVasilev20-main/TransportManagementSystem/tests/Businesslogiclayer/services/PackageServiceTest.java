package Businesslogiclayer.services;

import Businesslogiclayer.services.PackageService;
import Dataaccesslayer.models.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PackageServiceTest {
    // Instance of PackageService to be tested
    private PackageService packageService;

    // Set up the PackageService instance before each test
    @BeforeEach
    void setUp() {
        packageService = PackageService.getInstance();
    }

    // Test to retrieve a list of packages
    @Test
    void getPackages() {
        List<Package> packages = packageService.getPackages();
        assertNotNull(packages);
    }

    // Test to retrieve a package by its ID
    @Test
    void getPackageById() {
        Package pack = packageService.getPackageById(1);
        assertNotNull(pack);
    }

    // Test to add and delete a package
    @Test
    void deletePackage() {
        // Create a new package to be deleted
        Package newPackage = new Package(0, 1, 2, "ToDelete", "In Transit", "Warehouse", "2024-01-30");
        packageService.addPackage(newPackage);

        // Get the ID of the newly added package
        int packageId = packageService.getPackageIds().stream().max(Integer::compareTo).orElse(-1);

        // Delete the package
        packageService.deletePackage(packageId);

        // Retrieve the list of packages and ensure the deleted package is not present
        List<Package> packages = packageService.getPackages();
        assertFalse(packages.stream().anyMatch(p -> p.getDescription().equals("ToDelete")));
    }

    // Test to retrieve a list of package IDs
    @Test
    void getPackageIds() {
        List<Integer> packageIds = packageService.getPackageIds();
        assertNotNull(packageIds);
    }
}
