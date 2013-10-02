#!/usr/bin/env perl

use Modern::Perl;
use Math::Prime::FastSieve qw( primes );

# ----------   The object (far more flexible) interface.   ----------
 
# # Create a new sieve and flag all primes less or equal to n.
# my $sieve = Math::Prime::FastSieve::Sieve->new( 1_000_000 );
 
# # Obtain a ref to an array containing all primes <= 1 Million.
# my $aref = $sieve->primes( 1_000_000 );
 
# # Obtain a ref to an array containing all primes >= 5, and <= 16.
# my $aref = $sieve->ranged_primes( 5, 16 );
 
# # Query the sieve: Is 17 prime? Return a true or false value.
# my $result = $sieve->isprime( 17 );
 
# # Get the value of the nearest prime less than or equal to 42.
# my $less_or_equal = $sieve->nearest_le( 42 );
 
# # Get the value of the nearest prime greater than or equal to 42.
# my $greater_or_equal = $sieve->nearest_ge( 42 );
 
# # Count how many primes exist within the sieve (ie, count all primes less
# # than or equal to 5 Million, assuming we instantiated the sieve with
# # Math::Prime::FastSieve::Sieve->new( 5_000_000 );.
# my $num_found = $sieve->count_sieve();
 
# # Count how many primes fall between 1 and 42 inclusive.
# my $quantity_le = $sieve->count_le( 42 );
 
# # Return the value of the 42nd prime number.
# my $fourty_second_prime = $sieve->nth_prime( 42 );

# -------------------------------------------------------------------
my $max = 1_000_000_100;
my $sieve = Math::Prime::FastSieve::Sieve->new( $max );
my $deg = 2;
while ( 10**$deg < $max) {
	say "10^" . $deg . ":\t" . $sieve->nearest_le( 10**$deg ) . "\t" . $sieve->nearest_ge( 10**$deg );
	$deg++;
}
