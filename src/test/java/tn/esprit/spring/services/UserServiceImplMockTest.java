package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.UserServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        // Préparation des données de test
        Role role = Role.CHEF_DEPARTEMENT;  // Exemple de rôle
        Date dateNaissance = new Date();  // Date actuelle pour la naissance
        User user = new User("John", "Doe", dateNaissance, role);  // Création d'un utilisateur

        // Simulation du comportement du repository
        when(userRepository.save(any(User.class))).thenReturn(user);  // Mock du save

        // Appel de la méthode à tester
        User result = userService.addUser(user);

        // Vérifications
        assertNotNull(result);  // Vérifie que l'utilisateur retourné n'est pas nul
        assertEquals("John", result.getFirstName());  // Vérifie le prénom
        assertEquals("Doe", result.getLastName());  // Vérifie le nom
        assertEquals(role, result.getRole());  // Vérifie le rôle
        assertEquals(dateNaissance, result.getDateNaissance());  // Vérifie la date de naissance
        verify(userRepository, times(1)).save(user);  // Vérifie que save a été appelé une fois
    }

    @Test
    public void testUpdateUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.updateUser(user);

        // Perform any verifications or assertions you need
        verify(userRepository).save(user); // Example of checking save method call
    }

    @Test
    public void testDeleteUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.deleteUser(String.valueOf(1L));

        // Verify if deleteById was called
        verify(userRepository).deleteById(anyLong());
    }
    @Test
    public void testRetrieveUser() {
        // Préparation des données de test
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);
        Long userId = 1L;

        // Simulation du comportement du repository
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Appel de la méthode à tester
        User result = userService.retrieveUser(String.valueOf(userId));

        // Vérifications
        assertNotNull(result);  // Vérifie que l'utilisateur retourné n'est pas nul
        assertEquals("John", result.getFirstName());  // Vérifie le prénom
        assertEquals("Doe", result.getLastName());  // Vérifie le nom
        assertEquals(Role.TECHNICIEN, result.getRole());  // Vérifie le rôle
        verify(userRepository, times(1)).findById(userId);  // Vérifie que findById a été appelé une fois
    }



}
